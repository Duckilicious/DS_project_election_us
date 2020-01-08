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
      protos.VotingService.VoteRequest> getVoteMethod;

  public static io.grpc.MethodDescriptor<protos.VotingService.VoteRequest,
      protos.VotingService.VoteRequest> getVoteMethod() {
    io.grpc.MethodDescriptor<protos.VotingService.VoteRequest, protos.VotingService.VoteRequest> getVoteMethod;
    if ((getVoteMethod = VoteServiceGrpc.getVoteMethod) == null) {
      synchronized (VoteServiceGrpc.class) {
        if ((getVoteMethod = VoteServiceGrpc.getVoteMethod) == null) {
          VoteServiceGrpc.getVoteMethod = getVoteMethod = 
              io.grpc.MethodDescriptor.<protos.VotingService.VoteRequest, protos.VotingService.VoteRequest>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.VoteService", "Vote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.VoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.VoteRequest.getDefaultInstance()))
                  .setSchemaDescriptor(new VoteServiceMethodDescriptorSupplier("Vote"))
                  .build();
          }
        }
     }
     return getVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protos.VotingService.StartorStopRequest,
      protos.VotingService.StartorStopRequest> getStartorStopStateMethod;

  public static io.grpc.MethodDescriptor<protos.VotingService.StartorStopRequest,
      protos.VotingService.StartorStopRequest> getStartorStopStateMethod() {
    io.grpc.MethodDescriptor<protos.VotingService.StartorStopRequest, protos.VotingService.StartorStopRequest> getStartorStopStateMethod;
    if ((getStartorStopStateMethod = VoteServiceGrpc.getStartorStopStateMethod) == null) {
      synchronized (VoteServiceGrpc.class) {
        if ((getStartorStopStateMethod = VoteServiceGrpc.getStartorStopStateMethod) == null) {
          VoteServiceGrpc.getStartorStopStateMethod = getStartorStopStateMethod = 
              io.grpc.MethodDescriptor.<protos.VotingService.StartorStopRequest, protos.VotingService.StartorStopRequest>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.VoteService", "StartorStopState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.StartorStopRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.StartorStopRequest.getDefaultInstance()))
                  .setSchemaDescriptor(new VoteServiceMethodDescriptorSupplier("StartorStopState"))
                  .build();
          }
        }
     }
     return getStartorStopStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protos.VotingService.VoteDelete,
      protos.VotingService.VoteDelete> getDeleteVoteMethod;

  public static io.grpc.MethodDescriptor<protos.VotingService.VoteDelete,
      protos.VotingService.VoteDelete> getDeleteVoteMethod() {
    io.grpc.MethodDescriptor<protos.VotingService.VoteDelete, protos.VotingService.VoteDelete> getDeleteVoteMethod;
    if ((getDeleteVoteMethod = VoteServiceGrpc.getDeleteVoteMethod) == null) {
      synchronized (VoteServiceGrpc.class) {
        if ((getDeleteVoteMethod = VoteServiceGrpc.getDeleteVoteMethod) == null) {
          VoteServiceGrpc.getDeleteVoteMethod = getDeleteVoteMethod = 
              io.grpc.MethodDescriptor.<protos.VotingService.VoteDelete, protos.VotingService.VoteDelete>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.VoteService", "DeleteVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.VoteDelete.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.VoteDelete.getDefaultInstance()))
                  .setSchemaDescriptor(new VoteServiceMethodDescriptorSupplier("DeleteVote"))
                  .build();
          }
        }
     }
     return getDeleteVoteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protos.VotingService.ResultsRequest,
      protos.VotingService.ResultsRequest> getElectionResultsMethod;

  public static io.grpc.MethodDescriptor<protos.VotingService.ResultsRequest,
      protos.VotingService.ResultsRequest> getElectionResultsMethod() {
    io.grpc.MethodDescriptor<protos.VotingService.ResultsRequest, protos.VotingService.ResultsRequest> getElectionResultsMethod;
    if ((getElectionResultsMethod = VoteServiceGrpc.getElectionResultsMethod) == null) {
      synchronized (VoteServiceGrpc.class) {
        if ((getElectionResultsMethod = VoteServiceGrpc.getElectionResultsMethod) == null) {
          VoteServiceGrpc.getElectionResultsMethod = getElectionResultsMethod = 
              io.grpc.MethodDescriptor.<protos.VotingService.ResultsRequest, protos.VotingService.ResultsRequest>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "protos.VoteService", "ElectionResults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.ResultsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.VotingService.ResultsRequest.getDefaultInstance()))
                  .setSchemaDescriptor(new VoteServiceMethodDescriptorSupplier("ElectionResults"))
                  .build();
          }
        }
     }
     return getElectionResultsMethod;
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
        io.grpc.stub.StreamObserver<protos.VotingService.VoteRequest> responseObserver) {
      asyncUnimplementedUnaryCall(getVoteMethod(), responseObserver);
    }

    /**
     */
    public void startorStopState(protos.VotingService.StartorStopRequest request,
        io.grpc.stub.StreamObserver<protos.VotingService.StartorStopRequest> responseObserver) {
      asyncUnimplementedUnaryCall(getStartorStopStateMethod(), responseObserver);
    }

    /**
     */
    public void deleteVote(protos.VotingService.VoteDelete request,
        io.grpc.stub.StreamObserver<protos.VotingService.VoteDelete> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteVoteMethod(), responseObserver);
    }

    /**
     */
    public void electionResults(protos.VotingService.ResultsRequest request,
        io.grpc.stub.StreamObserver<protos.VotingService.ResultsRequest> responseObserver) {
      asyncUnimplementedUnaryCall(getElectionResultsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                protos.VotingService.VoteRequest,
                protos.VotingService.VoteRequest>(
                  this, METHODID_VOTE)))
          .addMethod(
            getStartorStopStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                protos.VotingService.StartorStopRequest,
                protos.VotingService.StartorStopRequest>(
                  this, METHODID_STARTOR_STOP_STATE)))
          .addMethod(
            getDeleteVoteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                protos.VotingService.VoteDelete,
                protos.VotingService.VoteDelete>(
                  this, METHODID_DELETE_VOTE)))
          .addMethod(
            getElectionResultsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                protos.VotingService.ResultsRequest,
                protos.VotingService.ResultsRequest>(
                  this, METHODID_ELECTION_RESULTS)))
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
        io.grpc.stub.StreamObserver<protos.VotingService.VoteRequest> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startorStopState(protos.VotingService.StartorStopRequest request,
        io.grpc.stub.StreamObserver<protos.VotingService.StartorStopRequest> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartorStopStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteVote(protos.VotingService.VoteDelete request,
        io.grpc.stub.StreamObserver<protos.VotingService.VoteDelete> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteVoteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void electionResults(protos.VotingService.ResultsRequest request,
        io.grpc.stub.StreamObserver<protos.VotingService.ResultsRequest> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getElectionResultsMethod(), getCallOptions()), request, responseObserver);
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
    public protos.VotingService.VoteRequest vote(protos.VotingService.VoteRequest request) {
      return blockingUnaryCall(
          getChannel(), getVoteMethod(), getCallOptions(), request);
    }

    /**
     */
    public protos.VotingService.StartorStopRequest startorStopState(protos.VotingService.StartorStopRequest request) {
      return blockingUnaryCall(
          getChannel(), getStartorStopStateMethod(), getCallOptions(), request);
    }

    /**
     */
    public protos.VotingService.VoteDelete deleteVote(protos.VotingService.VoteDelete request) {
      return blockingUnaryCall(
          getChannel(), getDeleteVoteMethod(), getCallOptions(), request);
    }

    /**
     */
    public protos.VotingService.ResultsRequest electionResults(protos.VotingService.ResultsRequest request) {
      return blockingUnaryCall(
          getChannel(), getElectionResultsMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<protos.VotingService.VoteRequest> vote(
        protos.VotingService.VoteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getVoteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.VotingService.StartorStopRequest> startorStopState(
        protos.VotingService.StartorStopRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStartorStopStateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.VotingService.VoteDelete> deleteVote(
        protos.VotingService.VoteDelete request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteVoteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.VotingService.ResultsRequest> electionResults(
        protos.VotingService.ResultsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getElectionResultsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_VOTE = 0;
  private static final int METHODID_STARTOR_STOP_STATE = 1;
  private static final int METHODID_DELETE_VOTE = 2;
  private static final int METHODID_ELECTION_RESULTS = 3;

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
              (io.grpc.stub.StreamObserver<protos.VotingService.VoteRequest>) responseObserver);
          break;
        case METHODID_STARTOR_STOP_STATE:
          serviceImpl.startorStopState((protos.VotingService.StartorStopRequest) request,
              (io.grpc.stub.StreamObserver<protos.VotingService.StartorStopRequest>) responseObserver);
          break;
        case METHODID_DELETE_VOTE:
          serviceImpl.deleteVote((protos.VotingService.VoteDelete) request,
              (io.grpc.stub.StreamObserver<protos.VotingService.VoteDelete>) responseObserver);
          break;
        case METHODID_ELECTION_RESULTS:
          serviceImpl.electionResults((protos.VotingService.ResultsRequest) request,
              (io.grpc.stub.StreamObserver<protos.VotingService.ResultsRequest>) responseObserver);
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
              .addMethod(getStartorStopStateMethod())
              .addMethod(getDeleteVoteMethod())
              .addMethod(getElectionResultsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
