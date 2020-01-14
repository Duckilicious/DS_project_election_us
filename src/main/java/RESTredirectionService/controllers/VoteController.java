package RESTredirectionService.controllers;

import RESTredirectionService.VoteError.VoteException;
import RESTredirectionService.models.Vote;
import VotingServerImpl.GrpcServer.VotingServerStubs;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import protos.VotingService;
import RESTredirectionService.Error.ResponseStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static RESTredirectionService.RedirectionServiceStart.zkservice;

@Slf4j
@RestController
@RequestMapping

public class VoteController {
    private HashMap<Integer, Vote> votes =
            new HashMap<>();
    private AtomicInteger eid = new AtomicInteger(0);
    private VotingServerImpl.GrpcServer.VotingServerStubs stub;

    VoteController() {
        votes.put(eid.getAndIncrement(), new
                Vote(1234567890,20,"new_york"));

    }
/** for testing **/
    /*@GetMapping("/votes")
    List<Vote> all() {
        //System.out.print("abcd");
        return new ArrayList<>(votes.values());
    }
    @GetMapping("/votes/{id}")
    Vote one(@PathVariable int id) {
        return votes.get(id);
    }
/**                   **/


    @PostMapping("/votes")
    void newVote(@RequestBody Vote newVote) {
        try {
            VotingService.VoteRequest vote = VotingService.VoteRequest
                    .newBuilder()
                    .setVoterId(newVote.getNational_security_number())
                    .setVoterCandidate(newVote.getCandidate()).build();
            int size = zkservice.getLiveNodes(newVote.getState()).size();
            Random rand = new Random();
            int i = rand.nextInt(size);
            String live_node = zkservice.getLiveNodes(newVote.getState()).get(i);
            String[] parts = live_node.split(":");

            VotingServerImpl.GrpcServer.VotingServerStubs stub = new VotingServerStubs(parts[0], Integer.parseInt(parts[1]));
            protos.VotingService.VoteRequest reply = stub.stub.vote(vote);
            stub.channel.shutdown();

            if(reply.getElectionEnded() && !reply.getVoteAccepted()) {
                log.error("Election are suspended");
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Election are suspended");
            }

            if (!reply.getVoteAccepted()) {
                log.error("Vote wasn't accepted user should get HTTP 500");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Please try to vote at a later time");
            }
            throw new ResponseStatusException(HttpStatus.OK, "Thanks for Voting");
        }
    catch(RuntimeException e) {
        ResponseStatusException ex = new ResponseStatusException(HttpStatus.ACCEPTED, ""); //dummy
        if(e.getClass() == ex.getClass())
            throw e;
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Please try to vote at a later time");
        }
    }
}

