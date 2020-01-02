package VotingServerImpl.GrpcServer;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import protos.VoteServiceGrpc;

public class VotingServerStubs {
    public VoteServiceGrpc.VoteServiceBlockingStub stub;
    private ManagedChannel channel;

    public VotingServerStubs (String host, int port) {
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
