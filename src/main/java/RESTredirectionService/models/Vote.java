package RESTredirectionService.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;

@Data
public class Vote {


    private int id;
    private int national_security_number;
    private String first_name;
    private String last_name;
    private String candidate;



    public Vote(@JsonProperty(value="id",required = true)int id,
                @JsonProperty(value = "national_security_number",
                        required = true)int national_security_number,
                @JsonProperty(value = "first_name",required = true)
                        String first_name,@JsonProperty(value = "last_name",
            required = true)String last_name,@JsonProperty(value = "candidate",
            required = true)String candidate) {
        this.id = id;
        this.national_security_number = national_security_number;
        this.first_name = first_name;
        this.last_name = last_name;
        this.candidate = candidate;
    }

}