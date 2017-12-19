package org.trump.vincent.netty.rpc.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vincent on 2017/12/19 0019.
 */
public class RPCRequest {
    private String requestId;

    private String className;

    private String methodName;

    private List<Map> requestParams;

    public RPCRequest(String requestId,String className,String methodName){
        this(className,methodName);
        this.requestId = requestId;
    }

    public RPCRequest(String className,String methodName){
        this.className = className;
        this.methodName = methodName;
    }

    public <T> List<Map> addRequestParam(String paramName,Class<T> type,T value){
        if(requestParams==null){
            requestParams = new ArrayList<>();
        }
        Map parameter = new HashMap<>();
        parameter.put("type",type.getCanonicalName());
        parameter.put("value",value);
        requestParams.add(parameter);
        return requestParams;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this);
    }

    public String getRequestId() {
        return requestId;
    }

    public RPCRequest setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public RPCRequest setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public RPCRequest setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public List<Map> getRequestParams() {
        return requestParams;
    }

    public RPCRequest setRequestParams(List<Map> requestParams) {
        this.requestParams = requestParams;
        return this;
    }

}
