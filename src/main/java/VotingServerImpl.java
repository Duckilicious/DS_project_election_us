import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import protos.VoteServiceGrpc;
import protos.VotingService;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;

    public class VotingServerImpl extends VoteServiceGrpc.VoteServiceImplBase {
        int id;
        private Server VoteServer;
        private boolean  isLeader;
        private ZooKeeper zk;
        private Watcher watcher;
        final private String zgroup_service = "VotingService";
        private String zgroup_name;

        public VotingServerImpl(int id, int port, String hostList, int ztimeout, String state) {
            this.id = id;
            zgroup_name = state;
            final String membership_app = "membership_app";
            try {
                VoteServer = ServerBuilder.forPort(port)
                        .addService(this)
                        .build()
                        .start();
                //connect to zookeeper here
                connect(hostList, ztimeout);

                /*create membership service in zookeeper*/

                //if app doesn't exist
                createGroup(zgroup_service);

                //if group doesn't exist
                createGroup(zgroup_service + zgroup_name);

                //join group (always);
                joinGroup(zgroup_service + "/"+ zgroup_name, zgroup_name, null);


                //assign yourself if you're leader or not
            } catch (IOException | InterruptedException | KeeperException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        @Override
        public void Vote(VotingService.VoteRequest request, VotingService.VoteStatus voteStatus){
            //check in zookeeper if leader
            /*if(leader){
                sync in your DB
                send request if something is changed
                send all memebers u
             */
            //else
                //send to leader
        }
        public ZooKeeper connect(String hosts, int sessionTimeout)
                throws IOException, InterruptedException {
            final CountDownLatch connectedSignal = new CountDownLatch(1);
            ZooKeeper zk = new ZooKeeper(hosts, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                        connectedSignal.countDown();
                    }
                }
            });
            connectedSignal.await();
            return zk;
        }
        public void createGroup(String groupName)
                throws KeeperException, InterruptedException {
            String path = "/" + groupName;
            zk.create(path,
                    null /* data */,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        }

        public String joinGroup(String groupName, String memberName, byte[] data)
                throws KeeperException, InterruptedException {
            String path = "/" + groupName + "/" + memberName + "-";
            String createdPath = zk.create(path,
                    data,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            return createdPath;
        }
}
