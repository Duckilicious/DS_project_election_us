package VotingServerImpl.util;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClusterData {

    public static final String LEADER_ELECTION = "/leader_election_app/";
    public static final String MEMBERSHIP_APP = "/membership_app/";

    private static ClusterData clusterdata = new ClusterData();

    public static ClusterData getClusterInfo(){
        return clusterdata;
    }

    //live nodes of the cluster
    private List<String> liveNodes = new ArrayList<>();

    //this will contain the leader name
    private String leader;

}
