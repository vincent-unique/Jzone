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

    public ExtZookeeper(String connectString, int sessionTimeout, Watcher watcher, boolean canBeReadOnly)throws IOException{
        super(connectString,sessionTimeout,watcher,canBeReadOnly);
    }

    /**
     * This constuctor will be used to recovery the departed session which is represented by the sessionId
     *
     * *******************************
     * @param connectString
     * @param sessionTimeout
     * @param watcher
     * @param sessionId
     * @param sessionPasswd
     * @throws IOException
     */
    public ExtZookeeper(String connectString, int sessionTimeout, Watcher watcher,
                     long sessionId, byte[] sessionPasswd)
            throws IOException{

        super(connectString,sessionTimeout,watcher,sessionId,sessionPasswd);
    }
}
