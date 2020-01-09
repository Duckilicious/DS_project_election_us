package RESTredirectionService.Error;

import org.springframework.http.HttpStatus;

@org.springframework.web.bind.annotation.ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Servers need time to recover")
public class ResponseStatus extends Exception {

}
