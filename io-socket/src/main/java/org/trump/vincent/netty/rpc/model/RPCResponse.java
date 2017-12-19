package org.trump.vincent.netty.rpc.model;

import com.google.gson.Gson;

/**
 * Created by Vincent on 2017/12/19 0019.
 */
public class RPCResponse<T> {

    /**
     * the id is pointed the RPCRequest 's requestId
     */
    private String requestId;
    /**
     * Similarly, HttpStatus
     */
    private Integer rpcStatus;

    private T responseBody;

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }


    public String getRequestId() {
        return requestId;
    }

    public RPCResponse setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public Integer getRpcStatus() {
        return rpcStatus;
    }

    public RPCResponse setRpcStatus(Integer rpcStatus) {
        this.rpcStatus = rpcStatus;
        return this;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public RPCResponse setResponseBody(T responseBody) {
        this.responseBody = responseBody;
        return this;
    }
}
