package org.trump.vincent.netty.rpc.core;

import org.trump.vincent.netty.rpc.model.RPCRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by Vincent on 2017/12/20 0020.
 */
public class RequestProxy<E> implements InvocationHandler {

    private Class<E> clazz;

    public RequestProxy(Class<E> clazz){
        this.clazz = clazz;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{

        RPCRequest request = new RPCRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
       /* request.setTypeParameters(method.getParameterTypes());
        request.setParameters(args);
*/
        RequestEmitterExecutor handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = handler.sendRequest(request);
        return callBack.start();

    }
}
