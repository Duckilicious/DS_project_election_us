package VotingServerImpl.GrpcServer;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
class voteCounter {
        private int candidate;
        private int counter;

        voteCounter(int candi, int c) {
            candidate = candi;
            counter = c;
        }
        void increaseCounter() {
            counter++;
        }
}
