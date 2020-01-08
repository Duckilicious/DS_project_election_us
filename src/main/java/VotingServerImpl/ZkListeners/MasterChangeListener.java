package VotingServerImpl.ZkListeners;


import java.util.Collections;
import java.util.List;

import VotingServerImpl.util.ClusterData;
import VotingServerImpl.util.Implmentation.ZkServiceImplmentation;
import VotingServerImpl.util.ZkServiceAPI;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;

@Setter
@Slf4j
public class MasterChangeListener implements IZkChildListener {

    private ZkServiceImplmentation zkServiceAPI;

    @Override
    public void handleChildChange(String path, List<String> currentChildren) throws Exception {

        if(currentChildren.isEmpty()) {
            throw new RuntimeException("No node exist in the cluster to be leader");
        }
        else {
            zkServiceAPI = new ZkServiceImplmentation(ClusterData.ZKhost_list);
            Collections.sort(currentChildren);
            String newLeader = currentChildren.get(0);
            String leader = zkServiceAPI.getZNodeData(path.concat("/").concat(newLeader));
            log.info("new leader is: {}", leader);
            zkServiceAPI.closeConnection();
            //Each node will now have the info of who is the leader
            ClusterData.getClusterInfo().setLeader(leader);
        }
    }
}
