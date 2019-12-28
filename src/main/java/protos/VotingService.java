// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Voting_service.proto

package protos;

public final class VotingService {
  private VotingService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface VoteRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protos.VoteRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 voter_id = 1;</code>
     */
    int getVoterId();

    /**
     * <code>int32 voter_candidate = 2;</code>
     */
    int getVoterCandidate();

    /**
     * <code>string time = 3;</code>
     */
    java.lang.String getTime();
    /**
     * <code>string time = 3;</code>
     */
    com.google.protobuf.ByteString
        getTimeBytes();
  }
  /**
   * Protobuf type {@code protos.VoteRequest}
   */
  public  static final class VoteRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protos.VoteRequest)
      VoteRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use VoteRequest.newBuilder() to construct.
    private VoteRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private VoteRequest() {
      voterId_ = 0;
      voterCandidate_ = 0;
      time_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private VoteRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              voterId_ = input.readInt32();
              break;
            }
            case 16: {

              voterCandidate_ = input.readInt32();
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              time_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protos.VotingService.internal_static_protos_VoteRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protos.VotingService.internal_static_protos_VoteRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protos.VotingService.VoteRequest.class, protos.VotingService.VoteRequest.Builder.class);
    }

    public static final int VOTER_ID_FIELD_NUMBER = 1;
    private int voterId_;
    /**
     * <code>int32 voter_id = 1;</code>
     */
    public int getVoterId() {
      return voterId_;
    }

    public static final int VOTER_CANDIDATE_FIELD_NUMBER = 2;
    private int voterCandidate_;
    /**
     * <code>int32 voter_candidate = 2;</code>
     */
    public int getVoterCandidate() {
      return voterCandidate_;
    }

    public static final int TIME_FIELD_NUMBER = 3;
    private volatile java.lang.Object time_;
    /**
     * <code>string time = 3;</code>
     */
    public java.lang.String getTime() {
      java.lang.Object ref = time_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        time_ = s;
        return s;
      }
    }
    /**
     * <code>string time = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTimeBytes() {
      java.lang.Object ref = time_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        time_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (voterId_ != 0) {
        output.writeInt32(1, voterId_);
      }
      if (voterCandidate_ != 0) {
        output.writeInt32(2, voterCandidate_);
      }
      if (!getTimeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, time_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (voterId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, voterId_);
      }
      if (voterCandidate_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, voterCandidate_);
      }
      if (!getTimeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, time_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof protos.VotingService.VoteRequest)) {
        return super.equals(obj);
      }
      protos.VotingService.VoteRequest other = (protos.VotingService.VoteRequest) obj;

      boolean result = true;
      result = result && (getVoterId()
          == other.getVoterId());
      result = result && (getVoterCandidate()
          == other.getVoterCandidate());
      result = result && getTime()
          .equals(other.getTime());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + VOTER_ID_FIELD_NUMBER;
      hash = (53 * hash) + getVoterId();
      hash = (37 * hash) + VOTER_CANDIDATE_FIELD_NUMBER;
      hash = (53 * hash) + getVoterCandidate();
      hash = (37 * hash) + TIME_FIELD_NUMBER;
      hash = (53 * hash) + getTime().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static protos.VotingService.VoteRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.VotingService.VoteRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.VotingService.VoteRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.VotingService.VoteRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.VotingService.VoteRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.VotingService.VoteRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.VotingService.VoteRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.VotingService.VoteRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.VotingService.VoteRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static protos.VotingService.VoteRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.VotingService.VoteRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.VotingService.VoteRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(protos.VotingService.VoteRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code protos.VoteRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protos.VoteRequest)
        protos.VotingService.VoteRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protos.VotingService.internal_static_protos_VoteRequest_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protos.VotingService.internal_static_protos_VoteRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protos.VotingService.VoteRequest.class, protos.VotingService.VoteRequest.Builder.class);
      }

      // Construct using protos.VotingService.VoteRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        voterId_ = 0;

        voterCandidate_ = 0;

        time_ = "";

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protos.VotingService.internal_static_protos_VoteRequest_descriptor;
      }

      public protos.VotingService.VoteRequest getDefaultInstanceForType() {
        return protos.VotingService.VoteRequest.getDefaultInstance();
      }

      public protos.VotingService.VoteRequest build() {
        protos.VotingService.VoteRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protos.VotingService.VoteRequest buildPartial() {
        protos.VotingService.VoteRequest result = new protos.VotingService.VoteRequest(this);
        result.voterId_ = voterId_;
        result.voterCandidate_ = voterCandidate_;
        result.time_ = time_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protos.VotingService.VoteRequest) {
          return mergeFrom((protos.VotingService.VoteRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protos.VotingService.VoteRequest other) {
        if (other == protos.VotingService.VoteRequest.getDefaultInstance()) return this;
        if (other.getVoterId() != 0) {
          setVoterId(other.getVoterId());
        }
        if (other.getVoterCandidate() != 0) {
          setVoterCandidate(other.getVoterCandidate());
        }
        if (!other.getTime().isEmpty()) {
          time_ = other.time_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        protos.VotingService.VoteRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protos.VotingService.VoteRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int voterId_ ;
      /**
       * <code>int32 voter_id = 1;</code>
       */
      public int getVoterId() {
        return voterId_;
      }
      /**
       * <code>int32 voter_id = 1;</code>
       */
      public Builder setVoterId(int value) {
        
        voterId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 voter_id = 1;</code>
       */
      public Builder clearVoterId() {
        
        voterId_ = 0;
        onChanged();
        return this;
      }

      private int voterCandidate_ ;
      /**
       * <code>int32 voter_candidate = 2;</code>
       */
      public int getVoterCandidate() {
        return voterCandidate_;
      }
      /**
       * <code>int32 voter_candidate = 2;</code>
       */
      public Builder setVoterCandidate(int value) {
        
        voterCandidate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 voter_candidate = 2;</code>
       */
      public Builder clearVoterCandidate() {
        
        voterCandidate_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object time_ = "";
      /**
       * <code>string time = 3;</code>
       */
      public java.lang.String getTime() {
        java.lang.Object ref = time_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          time_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string time = 3;</code>
       */
      public com.google.protobuf.ByteString
          getTimeBytes() {
        java.lang.Object ref = time_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          time_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string time = 3;</code>
       */
      public Builder setTime(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string time = 3;</code>
       */
      public Builder clearTime() {
        
        time_ = getDefaultInstance().getTime();
        onChanged();
        return this;
      }
      /**
       * <code>string time = 3;</code>
       */
      public Builder setTimeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        time_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:protos.VoteRequest)
    }

    // @@protoc_insertion_point(class_scope:protos.VoteRequest)
    private static final protos.VotingService.VoteRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new protos.VotingService.VoteRequest();
    }

    public static protos.VotingService.VoteRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<VoteRequest>
        PARSER = new com.google.protobuf.AbstractParser<VoteRequest>() {
      public VoteRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new VoteRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<VoteRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<VoteRequest> getParserForType() {
      return PARSER;
    }

    public protos.VotingService.VoteRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  public interface VoteStatusOrBuilder extends
      // @@protoc_insertion_point(interface_extends:protos.VoteStatus)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>bool status = 1;</code>
     */
    boolean getStatus();
  }
  /**
   * Protobuf type {@code protos.VoteStatus}
   */
  public  static final class VoteStatus extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:protos.VoteStatus)
      VoteStatusOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use VoteStatus.newBuilder() to construct.
    private VoteStatus(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private VoteStatus() {
      status_ = false;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private VoteStatus(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              status_ = input.readBool();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return protos.VotingService.internal_static_protos_VoteStatus_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protos.VotingService.internal_static_protos_VoteStatus_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protos.VotingService.VoteStatus.class, protos.VotingService.VoteStatus.Builder.class);
    }

    public static final int STATUS_FIELD_NUMBER = 1;
    private boolean status_;
    /**
     * <code>bool status = 1;</code>
     */
    public boolean getStatus() {
      return status_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (status_ != false) {
        output.writeBool(1, status_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (status_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, status_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof protos.VotingService.VoteStatus)) {
        return super.equals(obj);
      }
      protos.VotingService.VoteStatus other = (protos.VotingService.VoteStatus) obj;

      boolean result = true;
      result = result && (getStatus()
          == other.getStatus());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + STATUS_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getStatus());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static protos.VotingService.VoteStatus parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.VotingService.VoteStatus parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.VotingService.VoteStatus parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.VotingService.VoteStatus parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.VotingService.VoteStatus parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.VotingService.VoteStatus parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.VotingService.VoteStatus parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.VotingService.VoteStatus parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.VotingService.VoteStatus parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static protos.VotingService.VoteStatus parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.VotingService.VoteStatus parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.VotingService.VoteStatus parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(protos.VotingService.VoteStatus prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code protos.VoteStatus}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:protos.VoteStatus)
        protos.VotingService.VoteStatusOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protos.VotingService.internal_static_protos_VoteStatus_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protos.VotingService.internal_static_protos_VoteStatus_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protos.VotingService.VoteStatus.class, protos.VotingService.VoteStatus.Builder.class);
      }

      // Construct using protos.VotingService.VoteStatus.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        status_ = false;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protos.VotingService.internal_static_protos_VoteStatus_descriptor;
      }

      public protos.VotingService.VoteStatus getDefaultInstanceForType() {
        return protos.VotingService.VoteStatus.getDefaultInstance();
      }

      public protos.VotingService.VoteStatus build() {
        protos.VotingService.VoteStatus result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public protos.VotingService.VoteStatus buildPartial() {
        protos.VotingService.VoteStatus result = new protos.VotingService.VoteStatus(this);
        result.status_ = status_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof protos.VotingService.VoteStatus) {
          return mergeFrom((protos.VotingService.VoteStatus)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protos.VotingService.VoteStatus other) {
        if (other == protos.VotingService.VoteStatus.getDefaultInstance()) return this;
        if (other.getStatus() != false) {
          setStatus(other.getStatus());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        protos.VotingService.VoteStatus parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protos.VotingService.VoteStatus) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private boolean status_ ;
      /**
       * <code>bool status = 1;</code>
       */
      public boolean getStatus() {
        return status_;
      }
      /**
       * <code>bool status = 1;</code>
       */
      public Builder setStatus(boolean value) {
        
        status_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bool status = 1;</code>
       */
      public Builder clearStatus() {
        
        status_ = false;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:protos.VoteStatus)
    }

    // @@protoc_insertion_point(class_scope:protos.VoteStatus)
    private static final protos.VotingService.VoteStatus DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new protos.VotingService.VoteStatus();
    }

    public static protos.VotingService.VoteStatus getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<VoteStatus>
        PARSER = new com.google.protobuf.AbstractParser<VoteStatus>() {
      public VoteStatus parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new VoteStatus(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<VoteStatus> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<VoteStatus> getParserForType() {
      return PARSER;
    }

    public protos.VotingService.VoteStatus getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protos_VoteRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protos_VoteRequest_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_protos_VoteStatus_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_protos_VoteStatus_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024Voting_service.proto\022\006protos\"F\n\013VoteRe" +
      "quest\022\020\n\010voter_id\030\001 \001(\005\022\027\n\017voter_candida" +
      "te\030\002 \001(\005\022\014\n\004time\030\003 \001(\t\"\034\n\nVoteStatus\022\016\n\006" +
      "status\030\001 \001(\0102@\n\013VoteService\0221\n\004Vote\022\023.pr" +
      "otos.VoteRequest\032\022.protos.VoteStatus\"\000b\006" +
      "proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_protos_VoteRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_protos_VoteRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protos_VoteRequest_descriptor,
        new java.lang.String[] { "VoterId", "VoterCandidate", "Time", });
    internal_static_protos_VoteStatus_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_protos_VoteStatus_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_protos_VoteStatus_descriptor,
        new java.lang.String[] { "Status", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
