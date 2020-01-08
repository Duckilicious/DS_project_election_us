package VotingServerImpl.GrpcServer;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import protos.VoteServiceGrpc;

@Getter
public class VotingServerStubs {
    public VoteServiceGrpc.VoteServiceBlockingStub stub;
    private ManagedChannel channel;
    String Host;
    int Port;

    public VotingServerStubs (String host, int port) {
        Host = host;
        Port = port;
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .intercept(new Decorator(0))
                .usePlaintext()
                .build();
        stub = VoteServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() {
        channel.shutdown();
    }
}
