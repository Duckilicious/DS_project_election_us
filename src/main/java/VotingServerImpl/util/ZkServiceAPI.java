package VotingServerImpl.util;

import java.util.List;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;

public interface ZkServiceAPI {

    String getLeaderNodeData(String state);

    void addToLiveNodes(String nodeName, String data, String state);

    List<String> getLiveNodes(String state);

    void createAllParentNodes(String state);

    String getZNodeData(String path);

    void createNodeInElectionZnode(String data, String state);

    void registerChildrenChangeWatcher(String path, IZkChildListener iZkChildListener);

}