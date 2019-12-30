package VotingServerImpl.util;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostIP {
    private static String ip = null;

    public static String getIp() {
        if(ip != null) {
            return ip;
        }
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("failed to fetch IP", e);
        }
        return ip;
    }
}
