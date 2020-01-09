package VotingServerImpl.GrpcServer;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import protos.VoteServiceGrpc;

import java.io.IOException;

@Getter
public class VotingServerStubs {
    public VoteServiceGrpc.VoteServiceBlockingStub stub;
    public ManagedChannel channel;
    String Host;
    int Port;

    public VotingServerStubs (String host, int port) {
        Host = host;
        Port = port;
        try {
            channel = ManagedChannelBuilder
                    .forAddress(host, port)
                    .intercept(new Decorator(0))
                    .usePlaintext()
                    .build();
            stub = VoteServiceGrpc.newBlockingStub(channel);
        }
        catch (Exception e){
            if(channel != null){
                while(!channel.isTerminated())
                    channel.shutdown();
            }
            throw new RuntimeException();
        }
    }

    public void shutdown() {
        channel.shutdown();
    }
}
