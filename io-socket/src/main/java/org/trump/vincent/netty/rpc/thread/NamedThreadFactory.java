package org.trump.vincent.netty.rpc.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vincent on 2017/12/20 0020.
 */
public class NamedThreadFactory implements ThreadFactory {

    private String threadNamePrefix = "RPCServer-threadpool";
    private static final AtomicInteger threadNum = new AtomicInteger(1);
    /**
     * The style for thread running
     */
    private boolean deamon = false;

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    private final ThreadGroup threadGroup;


    public NamedThreadFactory(){
        this(false);
    }

    public NamedThreadFactory(boolean isDeamon){
        this("RPCServer-threadpool",isDeamon);
    }

    public NamedThreadFactory(String prefix,boolean isDeamon){
        this.threadNamePrefix = prefix;
        this.deamon = isDeamon;

        SecurityManager securityManager = System.getSecurityManager();
        this.threadGroup = securityManager==null?
                Thread.currentThread().getThreadGroup():
                    securityManager.getThreadGroup();
    }
    public Thread newThread(Runnable runnable){
        String threadName = this.threadNamePrefix+"_"+threadNum.getAndIncrement();
        Thread thread = new Thread(this.threadGroup,threadName);
        thread.setDaemon(this.deamon);
        return thread;
    }
}
