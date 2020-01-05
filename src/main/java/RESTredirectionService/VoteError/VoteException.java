package RESTredirectionService.VoteError;

import org.springframework.stereotype.Component;

@Component
public class VoteException extends RuntimeException{

    private String httpMessage;
    private String detailedMessage;

    public VoteException() {
    }

    public VoteException(String httpMessage, String detailedMessage) {
        this.httpMessage = httpMessage;
        this.detailedMessage = detailedMessage;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        this.httpMessage = httpMessage;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public void setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
    }
}
