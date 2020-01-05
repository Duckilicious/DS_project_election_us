package RESTredirectionService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Vote {


    private int national_security_number;
    private int candidate;
    private String state;



    public Vote(@JsonProperty(value = "national_security_number", required = true) int national_security_number,
            @JsonProperty(value = "candidate", required = true) int candidate,
                @JsonProperty(value = "state", required = true)String state) {
        this.national_security_number = national_security_number;
        this.candidate = candidate;
        this.state = state;
    }

}