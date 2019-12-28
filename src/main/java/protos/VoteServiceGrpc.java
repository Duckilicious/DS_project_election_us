package protos;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: Voting_service.proto")
public final class VoteServiceGrpc {

  private VoteServiceGrpc() {}

  public static final String SERVICE_NAME = "protos.VoteService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protos.VotingService.VoteRequest,
      protos.VotingService.VoteStatus> getVoteMethod;

  public static io.grpc.MethodDescriptor<protos.VotingService.VoteRequest,
      protos.VotingService.VoteStatus> getVoteMethod() {
    io.grpc.MethodDescriptor<protos.VotingService.VoteRequest, protos.VotingService.VoteStatus> getVoteMethod;
    if ((getVoteMethod = VoteServiceGrpc.getVoteMethod) == null) {
      synchronized (VoteServiceGrpc.class) {
        if ((getVoteMethod = VoteServiceGrpc.getVoteMethod) == null) {
          VoteServiceGrpc.getVoteMethod = getVoteMethod = 
              io.grpc.MethodDescriptor.<protos.VotingService.VoteRequest, protos.VotingService.VoteStatus>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.VoteService", "Vote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.VoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.VoteStatus.getDefaultInstance()))
                  .setSchemaDescriptor(new VoteServiceMethodDescriptorSupplier("Vote"))
                  .build();
          }
        }
     }
     return getVoteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VoteServiceStub newStub(io.grpc.Channel channel) {
    return new VoteServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VoteServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new VoteServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static VoteServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new VoteServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class VoteServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void vote(protos.VotingService.VoteRequest request,
        io.grpc.stub.StreamObserver<protos.VotingService.VoteStatus> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                protos.VotingService.VoteRequest,
                protos.VotingService.VoteStatus>(
                  this, METHODID_VOTE)))
          .build();
    }
  }

  /**
   */
  public static final class VoteServiceStub extends io.grpc.stub.AbstractStub<VoteServiceStub> {
    private VoteServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private VoteServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VoteServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new VoteServiceStub(channel, callOptions);
    }

    /**
     */
    public void vote(protos.VotingService.VoteRequest request,
        io.grpc.stub.StreamObserver<protos.VotingService.VoteStatus> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class VoteServiceBlockingStub extends io.grpc.stub.AbstractStub<VoteServiceBlockingStub> {
    private VoteServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private VoteServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VoteServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new VoteServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protos.VotingService.VoteStatus vote(protos.VotingService.VoteRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class VoteServiceFutureStub extends io.grpc.stub.AbstractStub<VoteServiceFutureStub> {
    private VoteServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private VoteServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VoteServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new VoteServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.VotingService.VoteStatus> vote(
        protos.VotingService.VoteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VOTE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final VoteServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(VoteServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_VOTE:
          serviceImpl.vote((protos.VotingService.VoteRequest) request,
              (io.grpc.stub.StreamObserver<protos.VotingService.VoteStatus>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class VoteServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    VoteServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protos.VotingService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("VoteService");
    }
  }

  private static final class VoteServiceFileDescriptorSupplier
      extends VoteServiceBaseDescriptorSupplier {
    VoteServiceFileDescriptorSupplier() {}
  }

  private static final class VoteServiceMethodDescriptorSupplier
      extends VoteServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    VoteServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (VoteServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new VoteServiceFileDescriptorSupplier())
              .addMethod(getVoteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
