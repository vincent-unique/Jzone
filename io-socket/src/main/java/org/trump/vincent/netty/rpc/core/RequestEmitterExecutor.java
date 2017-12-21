package org.trump.vincent.netty.rpc.core;

/**
 * Created by Vincent on 2017/12/19 0019.
 */

import java.lang.reflect.Proxy;

/**
 * @Reference http://www.jianshu.com/p/c9bffd1c475a
 */
public class RequestEmitterExecutor {
    private RpcServerLoader loader = RpcServerLoader.getInstance();

    public RequestEmitterExecutor(String serverAddress) {
        loader.load(serverAddress);
    }

    public void stop() {
        loader.unLoad();
    }

    public static <T> T execute(Class<T> rpcInterface) {
        return (T) Proxy.newProxyInstance(
                rpcInterface.getClassLoader(),
                new Class<?>[]{rpcInterface},
                new RequestProxy<T>(rpcInterface)
        );
    }
}
