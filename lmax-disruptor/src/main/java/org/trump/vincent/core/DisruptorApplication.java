package org.trump.vincent.core;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.trump.vincent.event.Event;
import org.trump.vincent.event.LogEvent;
import org.trump.vincent.event.LogEventFactory;
import org.trump.vincent.event.LogEventHandler;
import sun.rmi.runtime.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DisruptorApplication {

    private Disruptor<LogEvent> logEventDisruptor ;

    ExecutorService threadPool;

    public void config(){
        threadPool = new ThreadPoolExecutor(5,20,
                2, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(50));

        final EventFactory<LogEvent> logEventFactory = new LogEventFactory();
        final int ringBufferSize = 1024*1024;

         logEventDisruptor = new Disruptor<LogEvent>(logEventFactory,ringBufferSize,threadPool);

         logEventDisruptor.handleEventsWith(new LogEventHandler());
    }

    public void on(){
        if(logEventDisruptor==null){
            this.config();
        }
        this.logEventDisruptor.start();

        RingBuffer<LogEvent> logEventRingBuffer = this.logEventDisruptor.getRingBuffer();

        LogEvent logEvent = new LogEventFactory().newInstance();

        new Producer<LogEvent>(logEventRingBuffer).publish(logEvent);
    }

    public void close(){
        if(logEventDisruptor != null){
            this.logEventDisruptor.shutdown();
        }
        if(this.threadPool!=null){
            this.threadPool.shutdown();
        }
    }
}
