package VotingServerImpl.GrpcServer;

import VotingServerImpl.ZkListeners.MasterChangeListener;
import VotingServerImpl.ZkListeners.MembershipListener;
import VotingServerImpl.util.ClusterData;
import VotingServerImpl.util.HostIP;
import VotingServerImpl.util.Implmentation.ZkServiceImplmentation;
import VotingServerImpl.util.VoteInfo;
import VotingServerImpl.util.ZkServiceAPI;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.server.quorum.Vote;
import protos.VoteServiceGrpc;
import protos.VotingService;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import VotingServerImpl.util.Implmentation.*;

//TODO: change from checking zookeeper all the time to checkiong the ClusterInfo struct

import org.apache.zookeeper.*;
    @Slf4j
    public class VotingServerImpl extends VoteServiceGrpc.VoteServiceImplBase {
        private String HostName;
        private Server VoteServer;
        private ZkServiceImplmentation zkServiceAPI;
        private String cluster_state_name;
        private Boolean service_start;
        private List <VotingServerStubs> stubs;
        private HashMap<Integer, VoteInfo> votes;

        public VotingServerImpl(String port, String hostList, String state) {
            cluster_state_name = state;
            service_start = false;
            votes = new HashMap<>(0);
            int portNum = Integer.parseInt(port);
            try {
                VoteServer = ServerBuilder.forPort(portNum)
                        .addService(this)
                        .intercept(new Interceptor())
                        .build()
                        .start();
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

                zkServiceAPI.registerChildrenChangeWatcher(ClusterData.LEADER_ELECTION, new MasterChangeListener());
                log.info("add permneant watcher for election");

                zkServiceAPI.registerChildrenChangeWatcher(ClusterData.MEMBERSHIP_APP, new MembershipListener());
                log.info("add permneant watcher for membership");

                log.info("Host startup done" + HostName);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Startup failed!", e);
            }
        }

        public Boolean isNodeLeader(){
            return zkServiceAPI.getLeaderNodeData(cluster_state_name).equals(this.HostName);
        }

        private List<VotingServerStubs> updateAlive (List <String> alive_nodes) {

            //delete all previous stubs
            stubs.clear();

            //create a list with new members stub
            for (String temp : alive_nodes) {
                int i = 0;
                String[] parts = temp.split(":");
                VotingServerStubs stub = new VotingServerStubs(parts[0], Integer.parseInt(parts[1]));
                stubs.add(i, stub);
            }
            return stubs;
        }

        @Override
        public void vote(protos.VotingService.VoteRequest request,
                         io.grpc.stub.StreamObserver<protos.VotingService.VoteRequest> responseObserver) {
        if(service_start){
            if (isNodeLeader()) {
                int counter = 0;
                Date date = new Date();
                long time = date.getTime();
                Timestamp ts = new Timestamp(time);
                List <String> alive_nodes = zkServiceAPI.getLiveNodes(cluster_state_name);
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
                    protos.VotingService.VoteRequest reply = temp.stub.vote(newRequest);
                    if(reply.getLeaderSent()) {
                        counter ++;
                    }
                }

                //every alive member updated updated
                if (counter == alive_nodes.size()){

                    votes.put(request.getVoterId(),
                            new VoteInfo(request.getVoterId(),
                            Timestamp.valueOf(request.getTime()),
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
                    responseObserver.onNext(respond);
                    responseObserver.onCompleted();
                    log.info("Vote recorded for {}", request.getVoterId());
                }

                //updating failed -- keeping atomicity
                else {
                    int counter_delete = 0;
                    VoteInfo info = (votes.get(request.getVoterId()));
                    if(info != null) {
                        for (VotingServerStubs temp : stubs) {
                            VotingService.VoteDelete recent = VotingService.VoteDelete.newBuilder()
                                    .setVoterId(info.getVoterID())
                                    .setPreviousVote(info.getCandidate())
                                    .setPreviousTime(info.getTime().toString())
                                    .setDeleteSuccess(false)
                                    .build();
                            VotingService.VoteDelete reply = temp.stub.deleteVote(recent);
                            if(reply.getDeleteSuccess()) {
                                counter_delete++;
                            }
                            if (counter_delete != counter) {
                                log.info("Data might be inconsistent");
                            }
                        }

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
                        responseObserver.onNext(respond);
                        responseObserver.onCompleted();
                    }
                }
            }
            else {
                //We got the correct timestamp
                if (request.getLeaderSent()) {
                    //we need to respond to the redirection server or we need to respond to leader
                    if(request.getLeaderDone()) {
                        responseObserver.onNext(request);
                        responseObserver.onCompleted();
                    }
                    //See if we need to update
                    else {
                        VoteInfo info = votes.get(request.getVoterId());
                        if (info.getTime().before(Timestamp.valueOf(request.getTime()))) {
                            votes.remove(request.getVoterId());
                            votes.put(request.getVoterId(), new VoteInfo(request.getVoterId(),
                                    Timestamp.valueOf(request.getTime()),
                                    request.getVoterCandidate()));
                        }
                        VotingService.VoteRequest respond = VotingService.VoteRequest
                                .newBuilder()
                                .setAcceptedBy(HostName)
                                .setLeaderSent(false)
                                .setVoteAccepted(true)
                                .setVoterId(request.getVoterId())
                                .setTime(request.getTime())
                                .setLeaderDone(false)
                                .build();
                        responseObserver.onNext(respond);
                        responseObserver.onCompleted();
                    }
                }
                else {
                    String leader = zkServiceAPI.getLeaderNodeData(cluster_state_name);
                    String[] parts = leader.split(":");
                    VotingServerStubs leaderStub = new VotingServerStubs(parts[0], Integer.parseInt(parts[1]));
                    leaderStub.stub.vote(request);
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

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void startorStopState(protos.VotingService.StartorStopRequest request,
                               io.grpc.stub.StreamObserver<protos.VotingService.StartorStopRequest> responseObserver) {

            if (request.getFirst()) {
                List<String> nodes = zkServiceAPI.getLiveNodes(request.getState());
                nodes.remove(HostName);
                int startedCounter = 0;

                for (String temp : nodes) {
                    String[] parts = temp.split(":");
                    VotingServerStubs Stub = new VotingServerStubs(parts[0], Integer.parseInt(parts[1]));
                    VotingService.StartorStopRequest startOthers = VotingService.StartorStopRequest
                            .newBuilder()
                            .setState(request.getState())
                            .setFirst(false)
                            .setStartedCounter(0)
                            .setStartOrStop(request.getStartOrStop())
                            .build();
                    VotingService.StartorStopRequest reply = Stub.stub.startorStopState(startOthers);
                    if (reply.getStartedCounter() == 1) {
                        startedCounter++;
                    }

                    VotingService.StartorStopRequest replyClient = VotingService.StartorStopRequest
                            .newBuilder()
                            .setState(request.getState())
                            .setFirst(false)
                            .setStartedCounter(startedCounter)
                            .setStartOrStop(request.getStartOrStop())
                            .build();
                    responseObserver.onNext(replyClient);
                    responseObserver.onCompleted();
                }
                service_start = request.getStartOrStop();
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
                                    io.grpc.stub.StreamObserver<protos.VotingService.ResultsRequest> responseObserver){}


}
