package VotingServerImpl;

import VotingServerImpl.GrpcServer.VotingServerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
@Slf4j
public class VoteServerStart {
    public static void main(String[] args) {
        try {
            BasicConfigurator.configure();

            if(args.length == 3) {
                VotingServerImpl mainServer = new VotingServerImpl(args[0], args[1], args[2]);
                mainServer.start();
                log.info("Server started listening on port {}", args[0]);
                while(true);
            }
            else {
                throw new RuntimeException("Not enough argmuents");
            }
        }
        catch (Exception e){
            System.out.println("failed to start");
        }

    }
}
