package org.trump.vincent.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Vincent on 2017/12/27 0027.
 */
public class LogWatcher implements Watcher {

    Logger logger = LoggerFactory.getLogger(getClass());

    public void process(WatchedEvent event){

        if(event.getType().equals(Event.EventType.NodeChildrenChanged)) {
            logger.info("Report Children node Change:\n" + event.toString());
        }else if (event.getType().equals(Event.EventType.NodeDataChanged)){
            logger.info("Report node Data Change:\n"+event.toString());
        }
    }
}
