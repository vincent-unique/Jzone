package org.trump.vincent.netty.rpc.thread;

import io.netty.util.concurrent.RejectedExecutionHandlers;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Vincent on 2017/12/20 0020.
 */
public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {
    private final String threadName;

    public AbortPolicyWithReport(String name){
        this.threadName = name;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = String.format("RpcServer["
                        + " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d),"
                        + " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)]",
                threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());

        RuntimeException exception = new RejectedExecutionException(msg);
        exception.printStackTrace();
        throw exception;
    }
}
