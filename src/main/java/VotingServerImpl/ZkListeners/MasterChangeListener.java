package VotingServerImpl.ZkListeners;


import java.util.Collections;
import java.util.List;

import VotingServerImpl.util.ClusterData;
import VotingServerImpl.util.ZkServiceAPI;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

@Setter
@Slf4j
public class MasterChangeListener implements IZkChildListener {

    private ZkServiceAPI zkServiceAPI;

    @Override
    public void handleChildChange(String path, List<String> currentChildren) throws Exception {

        if(currentChildren.isEmpty()) {
            throw new RuntimeException("No node exist in the cluster to be leader");
        }
        else {
            Collections.sort(currentChildren);
            String newLeader = currentChildren.get(0);
            String leader = zkServiceAPI.getZNodeData(path.concat("/").concat(newLeader));
            log.info("new leader is: {}", leader);

            //Each node will now have the info of who is the leader
            ClusterData.getClusterInfo().setLeader(leader);
        }
    }
}
