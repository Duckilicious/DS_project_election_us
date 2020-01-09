package VotingServerImpl.util;

import java.util.ArrayList;
import java.util.List;

import VotingServerImpl.util.Implmentation.ZkServiceImplmentation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClusterData {

    public static final String LEADER_ELECTION = "/leader_election_app";
    public static final String MEMBERSHIP_APP = "/membership_app";
    public static final String INTEGRITY_APP = "/integrity_app";


    private static ClusterData clusterdata = new ClusterData();

    public static ClusterData getClusterInfo(){
        return clusterdata;
    }

    private ZkServiceImplmentation ZKhost;

    //live nodes of the cluster
    private List<String> liveNodes = new ArrayList<>();

    //this will contain the leader name
    private String leader;

}
