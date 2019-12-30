package VotingServerImpl.GrpcServer;

import VotingServerImpl.ZkListeners.MasterChangeListener;
import VotingServerImpl.ZkListeners.MembershipListener;
import VotingServerImpl.util.ClusterData;
import VotingServerImpl.util.HostIP;
import VotingServerImpl.util.Implmentation.ZkServiceImplmentation;
import VotingServerImpl.util.ZkServiceAPI;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import protos.VoteServiceGrpc;
import protos.VotingService;
import java.io.IOException;
import java.util.List;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import VotingServerImpl.util.Implmentation.*;


import org.apache.zookeeper.*;

    public class VotingServerImpl extends VoteServiceGrpc.VoteServiceImplBase {
        private int id;
        private String HostName;
        private Server VoteServer;
        private ZkServiceImplmentation zkServiceAPI;
        private String cluster_state_name;
        private Boolean service_start;

        public VotingServerImpl(int id, String port, String hostList, int ztimeout, String state) {
            this.id = id;
            cluster_state_name = state;
            service_start = false;
            int portNum = Integer.parseInt(port);
            try {
                VoteServer = ServerBuilder.forPort(portNum)
                        .addService(this)
                        .intercept(new Interceptor())
                        .build()
                        .start();
                HostName = HostIP.getIp()+":" + port;

                zkServiceAPI = new ZkServiceImplmentation(hostList);

                //Create main apps
                zkServiceAPI.createAllParentNodes(null);

                //Create state folder
                zkServiceAPI.createAllParentNodes(state);

                //join leadaer election app

                zkServiceAPI.createNodeInElectionZnode(HostIP.getIp() + ":" + port, state);
                ClusterData.getClusterInfo().setLeader(zkServiceAPI.getLeaderNodeData(state));

                /*
                    not required but  easy to implement - syncDatafromLeader
                 */
                //join membership app
                zkServiceAPI.addToLiveNodes(HostIP.getIp()+":" + port, HostIP.getIp()+":" + port , state);

                //add permneant watcher for election
                zkServiceAPI.registerChildrenChangeWatcher(ClusterData.LEADER_ELECTION, new MasterChangeListener());

                //add permneant watcher for membership
                zkServiceAPI.registerChildrenChangeWatcher(ClusterData.MEMBERSHIP_APP, new MembershipListener());

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Startuip failed!", e);
            }
        }

        public Boolean isNodeLeader(){
            return ClusterData.getClusterInfo().getLeader().equals(this.HostName);
        }

        @Override
        public void vote(protos.VotingService.VoteRequest request,
                         io.grpc.stub.StreamObserver<protos.VotingService.VoteStatus> responseObserver) {

            if (isNodeLeader()) {
                protos.VotingService.VoteStatus reply = protos.VotingService.VoteStatus
                        .newBuilder()
                        .build();
                responseObserver.onNext(reply);
            }
        }




}
