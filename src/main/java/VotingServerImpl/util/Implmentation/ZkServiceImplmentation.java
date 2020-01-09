package VotingServerImpl.util.Implmentation;

import VotingServerImpl.util.ClusterData;
import VotingServerImpl.util.StringSerializer;
import VotingServerImpl.util.ZkServiceAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

@Slf4j
public class ZkServiceImplmentation implements ZkServiceAPI {

    private ZkClient zkClient;

    public ZkServiceImplmentation(String zkServers) {
        zkClient = new ZkClient(zkServers , 12000, 3000 , new StringSerializer());
    }

    public void closeConnection(){
        zkClient.close();
    }


    @Override
    public String getLeaderNodeData(String state) {
        state = "/" + state;
        if(!zkClient.exists(ClusterData.LEADER_ELECTION + state)) {
            throw new RuntimeException("No node " + ClusterData.LEADER_ELECTION + "exists");
        }

        List<String> nodesInElection = zkClient.getChildren(ClusterData.LEADER_ELECTION + state);
        Collections.sort(nodesInElection);
        String leader = nodesInElection.get(0);
        return getZNodeData(ClusterData.LEADER_ELECTION + state + "/" + leader);
        }



    @Override
    public void addToLiveNodes(String nodeName, String data, String state) {
        state = "/" + state ;
        if(!zkClient.exists(ClusterData.MEMBERSHIP_APP)){
            zkClient.create(ClusterData.MEMBERSHIP_APP, "all live nodes are displayed here", CreateMode.PERSISTENT);
        }
        String childNode = ClusterData.MEMBERSHIP_APP + state + "/" + nodeName;
        if(zkClient.exists(childNode)){
            return;
        }
        zkClient.create(childNode, data, CreateMode.EPHEMERAL);
    }

    @Override
    public List<String> getLiveNodes(String state) {
        if(!state.equals("")) {
            if (!zkClient.exists(ClusterData.MEMBERSHIP_APP + "/" + state)) {
                throw new RuntimeException("No node /liveNodes exists");
            }
        }
        if(state.equals("")) {
            return zkClient.getChildren(ClusterData.MEMBERSHIP_APP);
        }
        else {
            return zkClient.getChildren(ClusterData.MEMBERSHIP_APP + "/" + state);
        }
    }

    public List<String> getUntrustedNodes() {
        if(!zkClient.exists(ClusterData.INTEGRITY_APP)) {
            zkClient.create( ClusterData.INTEGRITY_APP, "Nodes that can't be trusted", CreateMode.PERSISTENT);
        }
        return zkClient.getChildren(ClusterData.INTEGRITY_APP);
    }


    @Override
    public void createAllParentNodes(String state) {
        if(state != null){
            state = "/" + state;
        }
        else{
            state = "";
        }
        if (!zkClient.exists(ClusterData.MEMBERSHIP_APP +  state)) {
            zkClient.create(ClusterData.MEMBERSHIP_APP +  state, "all live nodes are displayed here", CreateMode.PERSISTENT);
        }
        if (!zkClient.exists(ClusterData.LEADER_ELECTION +  state)) {
            zkClient.create(ClusterData.LEADER_ELECTION +  state, "election node", CreateMode.PERSISTENT);
        }
        if(!zkClient.exists(ClusterData.INTEGRITY_APP)){
            zkClient.create( ClusterData.INTEGRITY_APP, "Nodes that can't be trusted", CreateMode.PERSISTENT);
        }

    }

    public void deleteIntegritiy() {
        List<String> to_delete = zkClient.getChildren(ClusterData.INTEGRITY_APP);
        for(String temp : to_delete) {
            zkClient.delete(ClusterData.INTEGRITY_APP.concat("/").concat(temp));
        }
    }

    public void createIntegrityNode(String nodeName){
        if(!zkClient.exists(ClusterData.INTEGRITY_APP.concat("/").concat(nodeName)))
            zkClient.create( ClusterData.INTEGRITY_APP.concat("/").concat(nodeName), "Data can't be blindly trusted", CreateMode.PERSISTENT);
    }


    @Override
    public String getZNodeData(String path) {
        return zkClient.readData(path, null);
    }

    @Override
    public void createNodeInElectionZnode(String data, String state) {
        //state += "/";
        if (!zkClient.exists(ClusterData.LEADER_ELECTION + "/" + state)) {
            zkClient.create(ClusterData.LEADER_ELECTION, "election node", CreateMode.PERSISTENT);
        }
        zkClient.create(ClusterData.LEADER_ELECTION + "/" + state + "/"  + "node", data, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    @Override
    public void registerChildrenChangeWatcher(String path, IZkChildListener iZkChildListener) {
        zkClient.subscribeChildChanges(path, iZkChildListener);

    }


}
