package org.trump.vincent.event;


import com.lmax.disruptor.EventHandler;

import java.util.logging.Logger;

public class LogEventHandler implements EventHandler<LogEvent> {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception{
        logger.info(event.getMessage());
        //TODO
    }
}
