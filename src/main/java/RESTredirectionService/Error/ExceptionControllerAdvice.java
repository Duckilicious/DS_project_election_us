package RESTredirectionService.Error;

import RESTredirectionService.VoteError.VoteException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {VoteException.class})
    protected ResponseEntity<Object> handleNotModified(RuntimeException voteException, WebRequest req) {
        String messageContent = "The vote didn't count. vote again, please!";
        return handleExceptionInternal(voteException, messageContent, new HttpHeaders(), HttpStatus.NOT_MODIFIED, req);
    }
}
  */

@ControllerAdvice
public class ExceptionControllerAdvice{

        @ExceptionHandler(VoteException.class)
        public ModelAndView handleException(VoteException voteException){
            ModelAndView model = new ModelAndView();
            model.addObject("http_code", voteException.getHttpMessage());
            model.addObject("details", voteException.getDetailedMessage());
            model.setViewName("error");
            return model;
        }

}





