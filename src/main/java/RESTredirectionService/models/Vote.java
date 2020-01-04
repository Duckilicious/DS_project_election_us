package RESTredirectionService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Vote {


    private int id;
    private int national_security_number;
    private String first_name;
    private String last_name;
    private int candidate;
    private String state;



    public Vote(@JsonProperty(value="id",required = true)int id,
                @JsonProperty(value = "national_security_number",
                        required = true)int national_security_number,
                @JsonProperty(value = "first_name",required = true)
                        String first_name,@JsonProperty(value = "last_name",
            required = true)String last_name,@JsonProperty(value = "candidate",
            required = true)int candidate,@JsonProperty(value = "state",
            required = true)String state) {
        this.id = id;
        this.national_security_number = national_security_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.candidate = candidate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNational_security_number() {
        return national_security_number;
    }

    public void setNational_security_number(int national_security_number) {
        this.national_security_number = national_security_number;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getCandidate() {
        return candidate;
    }

    public void setCandidate(int candidate) {
        this.candidate = candidate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}