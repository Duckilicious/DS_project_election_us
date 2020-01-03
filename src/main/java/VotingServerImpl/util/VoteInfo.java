package VotingServerImpl.util;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class VoteInfo {
    private int voterID;
    private Timestamp Time;
    private int Candidate;

    public VoteInfo(int voterid, Timestamp time, int candidate){
        voterID = voterid;
        Time = time;
        Candidate = candidate;
    }
}
