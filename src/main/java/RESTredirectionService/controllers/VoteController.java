package RESTredirectionService.controllers;


import RESTredirectionService.models.Vote;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping
public class VoteController {
    private HashMap<Integer, Vote> votes =
            new HashMap<>();
    private AtomicInteger eid = new AtomicInteger(0);

    VoteController() {
        votes.put(eid.getAndIncrement(), new
                Vote(1,1234567890,"Tal","Gelbard","Barack Obama"));
        votes.put(eid.getAndIncrement(), new
                Vote(2,545678,"Raz","Rippa","Barack Obama"));
    }

    @GetMapping("/votes")
    List<Vote> all() {
        //System.out.print("abcd");
        return new ArrayList<>(votes.values());
    }
    @GetMapping("/votes/{id}")
    Vote one(@PathVariable int id) {
        return votes.get(id);
    }

    @PostMapping("/votes")
    Vote newVote(@RequestBody Vote newVote) {
        votes.put(eid.getAndIncrement(), newVote);
        return newVote;
    }
}

