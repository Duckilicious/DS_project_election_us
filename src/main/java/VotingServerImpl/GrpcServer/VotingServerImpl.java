package VotingServerImpl.GrpcServer;

import VotingServerImpl.ZkListeners.MasterChangeListener;
import VotingServerImpl.ZkListeners.MembershipListener;
import VotingServerImpl.util.ClusterData;
import VotingServerImpl.util.HostIP;
import VotingServerImpl.util.Implmentation.ZkServiceImplmentation;
import VotingServerImpl.util.VoteInfo;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import protos.VoteServiceGrpc;
import protos.VotingService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//TODO: change from checking zookeeper all the time to checkiong the ClusterInfo struct

@Slf4j
    public class VotingServerImpl extends VoteServiceGrpc.VoteServiceImplBase {
        private String HostName;
        private Server VoteServer;
        public static ZkServiceImplmentation zkServiceAPI;
        private String cluster_state_name;
        private Boolean service_start;
        private ArrayList<VotingServerStubs> stubs;
        private HashMap<Integer, VoteInfo> votes;

        public VotingServerImpl(String port, String hostList, String state) {
            cluster_state_name = state;
            service_start = false;
            stubs = new ArrayList<VotingServerStubs>();
            votes = new HashMap<>(0);
            int portNum = Integer.parseInt(port);
            try {
                VoteServer = ServerBuilder.forPort(portNum)
                        .addService(this)
                        .intercept(new Interceptor())
                        .build();
                HostName = HostIP.getIp()+":" + port;
                log.info("Created Vote Server");

                zkServiceAPI = new ZkServiceImplmentation(hostList);
                log.info("Connected to Zookeeper");

                zkServiceAPI.createAllParentNodes(null);
                log.info("Created main folders on ZK");

                zkServiceAPI.createAllParentNodes(state);
                log.info("Created the state folder for ZK");

                zkServiceAPI.createNodeInElectionZnode(HostIP.getIp() + ":" + port, state);
                ClusterData.getClusterInfo().setLeader(zkServiceAPI.getLeaderNodeData(state));
                log.info("Joined election app for the state");

                /*
                    not required but  easy to implement - syncDatafromLeader
                 */

                zkServiceAPI.addToLiveNodes(HostIP.getIp()+":" + port, HostIP.getIp()+":" + port , state);
                log.info("Joined membership app for the state");

                zkServiceAPI.registerChildrenChangeWatcher(ClusterData.LEADER_ELECTION + "/"  + state, new MasterChangeListener());
                log.info("add permneant watcher for election");

                zkServiceAPI.registerChildrenChangeWatcher(ClusterData.MEMBERSHIP_APP + "/" + state , new MembershipListener());
                log.info("add permneant watcher for membership");

                zkServiceAPI.deleteIntegritiy();
                log.info("Deleted previous untrusted nodes");

                //init Cluster Data
                ClusterData.getClusterInfo().setZKhost(zkServiceAPI);
                ClusterData.getClusterInfo().setLeader(zkServiceAPI.getLeaderNodeData(cluster_state_name));
                ClusterData.getClusterInfo().setLiveNodes(zkServiceAPI.getLiveNodes(cluster_state_name));


                log.info("Host startup done" + HostName);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Startup failed!", e);
            }
        }

        private Boolean isNodeLeader(){
            return zkServiceAPI.getLeaderNodeData(cluster_state_name).equals(this.HostName);
        }

        public void start() {
            try {
                VoteServer.start();
            }
            catch (Exception e){
                throw new RuntimeException("server start failed", e);
            }
        }
        public void blockUntilShutdown() throws InterruptedException {
            if (VoteServer != null) {
                VoteServer.awaitTermination();
            }
        }

        private ArrayList<VotingServerStubs> updateAlive (List <String> alive_nodes) {

            //delete all previous stubs
            stubs.clear();

            //create a list with new members stub
            for (String temp : alive_nodes) {
                int i = 0;
                if(temp.equals(HostName))
                    continue;
                String[] parts = temp.split(":");
                VotingServerStubs stub = new VotingServerStubs(parts[0], Integer.parseInt(parts[1]));
                stubs.add(i, stub);
            }
            return stubs;
        }

        @Override
        public void vote(protos.VotingService.VoteRequest request,
                         io.grpc.stub.StreamObserver<protos.VotingService.VoteRequest> responseObserver) {
        /** Voting service down at the moment **/
        if(!service_start) {
            VotingService.VoteRequest reply = VotingService.VoteRequest.newBuilder()
                    .setVoteAccepted(false).setElectionEnded(true).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            log.info("ID {} tried to vote before or after the election started", request.getVoterId());
            return;
        }

        /** This node is the leader **/
        if (isNodeLeader()) {
            int counter = 0;
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            List <String> alive_nodes = ClusterData.getClusterInfo().getLiveNodes();
            alive_nodes.remove(HostName);
            stubs = updateAlive(alive_nodes);

            //Create a new request for all servers with a new time stamp
            protos.VotingService.VoteRequest newRequest = protos.VotingService.VoteRequest
                    .newBuilder()
                    .setLeaderSent(true)
                    .setTime(ts.toString())
                    .setVoterId(request.getVoterId())
                    .setVoterCandidate(request.getVoterCandidate())
                    .build();
            //
            log.info("Leader set time for vote, total order was established");
            for (VotingServerStubs temp : stubs) {
                try {
                    protos.VotingService.VoteRequest reply = temp.stub.vote(newRequest);
                    if (reply.getVoteAccepted())
                        counter++;
                    temp.channel.shutdown();
                }
                catch (Exception e) {
                   log.info("Leader wasn't able to communicate with all the cluster, rollback will be performed");
                }
            }
            //every alive member updated updated
            if (counter == alive_nodes.size()){
                log.info("Vote accepted by the entire cluster");
                votes.put(request.getVoterId(),
                        new VoteInfo(request.getVoterId(),
                        ts,
                        request.getVoterCandidate() ));
                protos.VotingService.VoteRequest respond = protos.VotingService.VoteRequest
                        .newBuilder()
                        .setLeaderSent(true)
                        .setTime(ts.toString())
                        .setVoterId(request.getVoterId())
                        .setVoteAccepted(true)
                        .setLeaderDone(true)
                        .setAcceptedBy("all cluster")
                        .setVoterCandidate(request.getVoterCandidate())
                        .build();
                try {
                    responseObserver.onNext(respond);
                    responseObserver.onCompleted();
                    log.info("Vote recorded for {}", request.getVoterId());

                }
                catch(Exception e) {
                    log.info("Wasn't able to send ack to the requester rolling back vote");
                    rollbackRequest(request, responseObserver, ts, false);
                }
            }
            //updating failed -- keeping atomicity
            else {
                    rollbackRequest(request, responseObserver, ts, true);
                }
        }
        //Node isn't leader
        else {
            //We got the correct timestamp
            if (request.getLeaderSent()) {
                //See if we need to update
                    VoteInfo info = votes.get(request.getVoterId());
                    if (info == null || info.getTime().before(Timestamp.valueOf(request.getTime()))) {
                        votes.remove(request.getVoterId());
                        votes.put(request.getVoterId(), new VoteInfo(request.getVoterId(),
                                Timestamp.valueOf(request.getTime()),
                                request.getVoterCandidate()));
                        log.info("Vote was recorded");
                    }
                    log.info("Trying to send ack to leader");
                    VotingService.VoteRequest respond = VotingService.VoteRequest
                            .newBuilder()
                            .setAcceptedBy(HostName)
                            .setLeaderSent(false)
                            .setVoteAccepted(true)
                            .setVoterId(request.getVoterId())
                            .setTime(request.getTime())
                            .setLeaderDone(false)
                            .build();

                        try {
                            responseObserver.onNext(respond);
                            responseObserver.onCompleted();
                        }
                        catch (Exception e) {
                            log.error("Was unable to send ack to leader, rolling back solo");
                            votes.remove(request.getVoterId());
                            if(info != null)
                                votes.put(info.getVoterID(), info);
                            log.error("Rollback Solo succeeded");
                        }
                }
            else {
                String leader = ClusterData.getClusterInfo().getLeader();
                String[] parts = leader.split(":");

                log.info("Sent to leader to create total order");

                try {
                    VotingServerStubs leaderStub = new VotingServerStubs(parts[0], Integer.parseInt(parts[1]));
                    VotingService.VoteRequest respond = leaderStub.stub.vote(request);
                    leaderStub.channel.shutdown();
                    if(respond.getLeaderDone()) {
                        try {
                            responseObserver.onNext(respond);
                            responseObserver.onCompleted();
                            log.info("Sending back info to REST server");
                        }
                        catch (Exception e) {
                            log.error("REST server wasn't able to accept ack, rolling back vote");
                            List <String> alive_nodes = ClusterData.getClusterInfo().getLiveNodes();
                            stubs = updateAlive(alive_nodes);
                            rollbackRequest(request, responseObserver, Timestamp.valueOf(request.getTime()), false);
                        }
                    }
                }
                catch (Exception e){
                    log.error("Leader unavailable failing the vote");
                    VotingService.VoteRequest respond = VotingService.VoteRequest.newBuilder()
                            .setVoteAccepted(false)
                            .build();
                    responseObserver.onNext(respond);
                    responseObserver.onCompleted();
                }
            }
            }

        }


        private void rollbackRequest(VotingService.VoteRequest request,
                                    io.grpc.stub.StreamObserver<protos.VotingService.VoteRequest> responseObserver,
                                    Timestamp ts, boolean sendBack) {
            int counter_delete = 0;
            updateAlive(ClusterData.getClusterInfo().getLiveNodes());
            VoteInfo info = (votes.get(request.getVoterId()));
            if (info != null) {
                for (VotingServerStubs temp : stubs) {
                    try {
                        VotingService.VoteDelete recent = VotingService.VoteDelete.newBuilder()
                                .setVoterId(info.getVoterID())
                                .setPreviousVote(info.getCandidate())
                                .setPreviousTime(info.getTime().toString())
                                .setDeleteSuccess(false)
                                .build();
                        VotingService.VoteDelete reply = temp.stub.deleteVote(recent);
                        if (reply.getDeleteSuccess()) {
                            counter_delete++;
                        }
                        temp.channel.shutdown();
                    }
                    catch (Exception e){
                        log.error("Could be that server" + temp.Host + Integer.toString(temp.Port) + "data is incossitent" +
                                "Creating an integrity node");
                        zkServiceAPI.createIntegrityNode(temp.Host + ":" + temp.Port);
                    }
                }
                log.info("Rollbacked {} servers", counter_delete);

                if(sendBack) {
                    protos.VotingService.VoteRequest respond = protos.VotingService.VoteRequest
                            .newBuilder()
                            .setLeaderSent(true)
                            .setTime(ts.toString())
                            .setVoterId(request.getVoterId())
                            .setVoteAccepted(false)
                            .setLeaderDone(true)
                            .setAcceptedBy("none")
                            .setVoterCandidate(request.getVoterCandidate())
                            .build();

                    try {
                        responseObserver.onNext(respond);
                        responseObserver.onCompleted();
                    }
                    catch (Exception e) {
                        log.info("Couldn't send back the request but rollback was performed");
                    }
                }

            }
        }

        @Override
        public void deleteVote(protos.VotingService.VoteDelete request,
                               io.grpc.stub.StreamObserver<protos.VotingService.VoteDelete> responseObserver){
            VoteInfo last = votes.get(request.getVoterId());
            if(last != null) {
                votes.remove(request.getVoterId());
                votes.put(request.getVoterId(), new VoteInfo(request.getVoterId(),
                        Timestamp.valueOf(request.getPreviousTime()),
                        request.getPreviousVote()));
            }
            VotingService.VoteDelete reply = VotingService.VoteDelete
                    .newBuilder()
                    .setVoterId(request.getVoterId())
                    .setPreviousVote(request.getPreviousVote())
                    .setPreviousTime(request.getPreviousTime())
                    .setDeleteSuccess(true)
                    .build();

            try {
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
            }
            catch (Exception e) {
                log.error("The server data was consistent, but this server won't be trusted");
            }
        }

        @Override
        public void startorStopState(protos.VotingService.StartorStopRequest request,
                               io.grpc.stub.StreamObserver<protos.VotingService.StartorStopRequest> responseObserver) {
            log.info("Received a startOrStop call");
            if (request.getFirst()) {
                List<String> nodes = ClusterData.getClusterInfo().getLiveNodes();
                nodes.remove(HostName);
                int startedCounter = 0;

                for (String temp : nodes) {
                    try {
                        String[] parts = temp.split(":");
                        VotingServerStubs Stub = new VotingServerStubs(parts[0], Integer.parseInt(parts[1]));
                        VotingService.StartorStopRequest startOthers = VotingService.StartorStopRequest
                                .newBuilder()
                                .setState(request.getState())
                                .setFirst(false)
                                .setStartedCounter(0)
                                .setStartOrStop(request.getStartOrStop())
                                .build();
                        log.info("sending request to:" + temp);
                        VotingService.StartorStopRequest reply = Stub.stub.startorStopState(startOthers);
                        if (reply.getStartedCounter() == 1) {
                            startedCounter++;
                        }
                        Stub.channel.shutdown();

                    }
                    catch (Exception e) {
                        VotingService.StartorStopRequest replyClient = VotingService.StartorStopRequest
                                .newBuilder()
                                .setState(request.getState())
                                .setFirst(true)
                                .setStartedCounter(startedCounter)
                                .setStartOrStop(request.getStartOrStop())
                                .setSuccess(false)
                                .build();
                        responseObserver.onNext(replyClient);
                        responseObserver.onCompleted();
                    }
                }
                startedCounter++;
                VotingService.StartorStopRequest replyClient = VotingService.StartorStopRequest
                        .newBuilder()
                        .setState(request.getState())
                        .setFirst(true)
                        .setStartedCounter(startedCounter)
                        .setSuccess(true)
                        .setStartOrStop(request.getStartOrStop())
                        .build();
                responseObserver.onNext(replyClient);
                responseObserver.onCompleted();
                service_start = request.getStartOrStop();
                log.info("service is now started: {}" + service_start.toString());
            }
            else {
                service_start = request.getStartOrStop();
                VotingService.StartorStopRequest sendToFirst = VotingService.StartorStopRequest
                        .newBuilder()
                        .setState(request.getState())
                        .setFirst(false)
                        .setStartOrStop(request.getStartOrStop())
                        .setStartedCounter(1)
                        .build();
                responseObserver.onNext(sendToFirst);
                responseObserver.onCompleted();
            }
        }




        @Override
        public void electionResults(protos.VotingService.ResultsRequest request,
                                    io.grpc.stub.StreamObserver<protos.VotingService.ResultsRequest> responseObserver){
            ArrayList<voteCounter> voteCounters = new ArrayList<voteCounter>() ;
            for (VoteInfo temp : votes.values()){
                voteCounter it = new voteCounter(-1, -1);
                for(voteCounter temp2 : voteCounters) {
                    if((temp2.getCandidate() == temp.getCandidate())) {
                        it = temp2;
                        break;
                    }

                }
                if( it.getCandidate() == -1 && it.getCounter() == -1 ){
                    voteCounters.add((new voteCounter(temp.getCandidate(),0)));
                }
                else {
                    it.increaseCounter();
                }
            }

            int candidateWithMaxVotes = 0;
            int maxVotes = 0;
            for (voteCounter temp : voteCounters) {
                if(temp.getCounter() > maxVotes) {
                    maxVotes = temp.getCounter();
                    candidateWithMaxVotes = temp.getCandidate();
                }
            }

            VotingService.ResultsRequest state_winner = VotingService.ResultsRequest
                    .newBuilder()
                    .setState(cluster_state_name)
                    .setCandidate(candidateWithMaxVotes)
                    .setElectors(request.getElectors())
                    .build();

            responseObserver.onNext(state_winner);
            responseObserver.onCompleted();
        }

    public static void main(String[] args) {
        try {
            BasicConfigurator.configure();
            Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            root.setLevel(Level.INFO);

            if(args.length == 3) {
                VotingServerImpl mainServer = new VotingServerImpl(args[0], args[1], args[2]);
                ClusterData.getClusterInfo().setZKhost(zkServiceAPI);
                mainServer.start();
                log.info("Server started listening on port {} ", args[0]);
                mainServer.blockUntilShutdown();
            }
            else {
                throw new RuntimeException("Not enough argmuents");
            }
        }
        catch (Exception e){
            System.out.println("failed to start");
        }

    }

}

