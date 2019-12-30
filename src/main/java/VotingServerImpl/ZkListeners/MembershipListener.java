package VotingServerImpl.ZkListeners;

import java.util.List;

import VotingServerImpl.util.ClusterData;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;

/**
 * This is the listening method for membership
 */
@Slf4j
public class MembershipListener implements IZkChildListener  {

    @Override
    public void handleChildChange(String parentName, List<String> currentChildren) throws Exception {
        ClusterData.getClusterInfo().getLiveNodes().clear();
        log.info("current live size: {}", currentChildren.size());
        ClusterData.getClusterInfo().getLiveNodes().clear();
        ClusterData.getClusterInfo().getLiveNodes().addAll(currentChildren);
    }
}
