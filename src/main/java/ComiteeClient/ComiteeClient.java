package ComiteeClient;

import VotingServerImpl.GrpcServer.VotingServerStubs;
import VotingServerImpl.util.Implmentation.ZkServiceImplmentation;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import protos.VotingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
public class ComiteeClient {
    private ZkServiceImplmentation zkServiceAPI;
    private ArrayList<String> stateList;
    private HashMap<String, Integer> StateElectors;

    public ComiteeClient(String Zkhosts) {
        zkServiceAPI = new ZkServiceImplmentation(Zkhosts);
        log.info("Connected to Zookeeper");

        fillStateElectors();
        List<String> states = zkServiceAPI.getLiveNodes("");
        stateList = new ArrayList<>();
        stateList.addAll(states);

        log.info("Gracefully disconnect from Zookeeper");
    }

    private void fillStateElectors() {
        StateElectors = new HashMap<String, Integer>();
        StateElectors.put("albama", 9);
        StateElectors.put("alska", 3);
        StateElectors.put("arkansas", 6);
        StateElectors.put("california", 55);
        StateElectors.put("colorado", 9);
        StateElectors.put("conneticut", 7);
        StateElectors.put("delaware", 3);
        StateElectors.put("district_of_columbia", 3);
        StateElectors.put("florida", 29);
        StateElectors.put("georgia", 16);
        StateElectors.put("hawaii", 4);
        StateElectors.put("idaho", 4);
        StateElectors.put("illinois", 20);
        StateElectors.put("indiana", 11);
        StateElectors.put("iowa", 6);
        StateElectors.put("kansas", 6);
        StateElectors.put("kentucky", 8);
        StateElectors.put("louisiana", 8);
        StateElectors.put("maine", 4);
        StateElectors.put("maryland", 10);
        StateElectors.put("massachusetts", 11);
        StateElectors.put("michigan", 16);
        StateElectors.put("minnesota", 10);
        StateElectors.put("mississippi", 6);
        StateElectors.put("missouri", 10);
        StateElectors.put("montana", 3);
        StateElectors.put("nebraska", 5);
        StateElectors.put("nevada", 6);
        StateElectors.put("new_hamsphire", 4);
        StateElectors.put("new_jersy", 14);
        StateElectors.put("new_mexico", 5);
        StateElectors.put("new_york", 29);
        StateElectors.put("north_carolina", 15);
        StateElectors.put("north_dakota", 3);
        StateElectors.put("ohio", 18);
        StateElectors.put("oklahoma", 7);
        StateElectors.put("oregon", 7);
        StateElectors.put("pennsylvania", 20);
        StateElectors.put("rhode_island", 4);
        StateElectors.put("south_carolina", 9);
        StateElectors.put("south_dakota", 3);
        StateElectors.put("tennesee", 11);
        StateElectors.put("texas", 38);
        StateElectors.put("utah", 6);
        StateElectors.put("vermont", 3);
        StateElectors.put("virginia", 13);
        StateElectors.put("washington", 12);
        StateElectors.put("west_virginia", 5);
        StateElectors.put("wisconsin", 10);
        StateElectors.put("wyoming", 3);
    }

    /**
     * @param state - the name of the state we start
     * @return how many hosts were started
     */
    public int starorStopOneCluster(String state, Boolean startOrStop) {

        VotingServerStubs stub = getStateStub(state);
        VotingService.StartorStopRequest start = VotingService.StartorStopRequest
                .newBuilder()
                .setStartOrStop(startOrStop)
                .setFirst(true)
                .setStartedCounter(0)
                .setState(state)
                .setStartedCounter(0)
                .build();

        try {
            VotingService.StartorStopRequest respond = stub.stub.startorStopState(start);
            stub.shutdown();
            return respond.getStartedCounter();
        }
        catch (Exception e){
            String request;
            if(startOrStop)
                request = "start";
            else
                request = "stop";
            log.error("Unable to {} {} cluster", request, state);
        }
        return 0;
    }

    public void startorStopAll(Boolean startOrStop) {
        int statesInCluster;
        String request;
        for(String temp : stateList) {
            try {
                statesInCluster = starorStopOneCluster(temp, startOrStop);
            }
            catch (Exception e) {
                log.error("No alive nodes in cluster" + temp);
                continue;
            }
            if(startOrStop)
                request = "start";
            else
                request = "stop";
            log.info("{} were {} in cluster {}", statesInCluster, request, temp);
        }
    }

    public int electionResultsForOneCluster(String state) {
        VotingServerStubs state_stub = getStateStub(state);
        VotingService.ResultsRequest request = VotingService.ResultsRequest
                .newBuilder()
                .setCandidate(-1)
                .setState(state)
                .setElectors(-1)
                .build();
        VotingService.ResultsRequest reply = state_stub.stub.electionResults(request);
        state_stub.shutdown();
        return reply.getCandidate();
    }

    public void electionResults() {
        ArrayList<Integer>  results = new ArrayList<Integer>();
        int winningStateCandidate;
        Integer winner = 0;


        for(String temp : stateList) {
            winningStateCandidate = electionResultsForOneCluster(temp);
            if(winningStateCandidate > results.size())
                results.set(winningStateCandidate, StateElectors.get(temp));
            else {
                results.set(winningStateCandidate, results.get(winningStateCandidate) + StateElectors.get(temp));
            }
        }

        for(Integer temp : results) {
            if(temp > winner) {
                winner = results.indexOf(temp);
            }
        }

        System.out.println("The winner of the election is" + Integer.toString(winner) + "with num of electors" +
                results.get(winner));
    }

    public VotingServerStubs getStateStub(String state) {
        List<String> hostNames = zkServiceAPI.getLiveNodes(state);
        if(hostNames.size()  == 0){
            throw new RuntimeException();
        }
        String hostname = hostNames.get(0);
        String[] ipPort = hostname.split(":");
        return new VotingServerStubs(ipPort[0], Integer.parseInt(ipPort[1]));
    }

    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Missing Zookeeper host");
            System.exit(0);
        }

        BasicConfigurator.configure();
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        ComiteeClient client = new ComiteeClient(args[0]);
          switch (args[1])
          {
              case "start_all" :
                  client.startorStopAll(true);
                  break;

              case "stop_all" :
                  client.startorStopAll(false);
                  break;

              case "get_results" :
                  client.electionResults();
                  break;
              default:
                  System.out.print(args[1] + "Command not recognized");
          }
        client.zkServiceAPI.closeConnection();
    }
}
