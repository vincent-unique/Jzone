package org.trump.vincent.netty.rpc.thread;

import java.util.concurrent.*;

/**
 * Created by Vincent on 2017/12/20 0020.
 */
public class RPCThreadPool {

    public static Executor buildExecutor(int threadNum, int queueNum) {
        String poolName = "RPC-ExecutorService";
        return new ThreadPoolExecutor(threadNum,
                threadNum,
                0, TimeUnit.MILLISECONDS,
                    queueNum == 0 ? new SynchronousQueue<Runnable>()
                        : (queueNum < 0 ? new LinkedBlockingQueue<Runnable>()
                        : new LinkedBlockingQueue<Runnable>(queueNum)),

                new NamedThreadFactory(poolName, true), new AbortPolicyWithReport(poolName));
    }

}
