package org.trump.vincent.zookeeper.core;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by Vincent on 2017/12/7 0007.
 */
public class ExtZookeeper extends ZooKeeper{

    public ExtZookeeper(String connectString, int sessionTimeout, Watcher watcher) throws IOException{
        super(connectString,sessionTimeout,watcher);
    }
}
