package org.trump.vincent.event;

import com.lmax.disruptor.EventFactory;

import java.util.UUID;

public class LogEventFactory implements EventFactory<LogEvent> {

    public LogEvent newInstance(){
        return new LogEvent().setEventId(UUID.randomUUID().toString());
    }
}
