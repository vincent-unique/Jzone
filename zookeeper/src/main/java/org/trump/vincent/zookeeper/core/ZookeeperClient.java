package org.trump.vincent.zookeeper.core;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by Vincent on 2017/12/7 0007.
 */
public class ZookeeperClient {
    private String zkServerAddress;
    private Integer zkClientTimeout = 5000;

    /**
     * ReadOnly Mode ,Clent chould read from the zk server when quarum less half the numbers in the cluster.
     * Default ,it is false
     */
    private boolean canReadOnly = false;

    /**
     * Revovery a session with the sessionId and sessionPwd
     */
    private long sessionId;
    private byte[] sessionPwd;

    private volatile boolean isClosed;

    private volatile ZooKeeper zooKeeper;

    private Watcher watcher;

    public ZookeeperClient(String zkConnections,Integer timeout)throws IOException{
       this(zkConnections,timeout,null);
    }

    public ZookeeperClient(String zkConnections,Integer timeout,Watcher watcher)throws IOException{
        this.zkServerAddress = zkConnections;
        this.zkClientTimeout = timeout;
        this.watcher = watcher;
        this.zooKeeper = new ExtZookeeper(this.zkServerAddress,this.zkClientTimeout,this.watcher);
    }
    public ZookeeperClient(String zkConnections,Integer timeout,Watcher watcher,boolean canReadOnly)throws IOException{
        this.zkServerAddress = zkConnections;
        this.zkClientTimeout = timeout;
        this.watcher = watcher;
        this.canReadOnly = canReadOnly;

        this.zooKeeper = new ExtZookeeper(this.zkServerAddress,this.zkClientTimeout,this.watcher,this.canReadOnly);
    }

    public ZookeeperClient(String zkConnections,Integer timeout,Watcher watcher,long sessionId, byte[] sessionPassWord)throws IOException {
        this.zkServerAddress = zkConnections;
        this.zkClientTimeout = timeout;
        this.watcher = watcher;
        this.sessionId = sessionId;
        this.sessionPwd = sessionPassWord;

        this.zooKeeper = new ExtZookeeper(this.zkServerAddress,this.zkClientTimeout,this.watcher,this.sessionId,this.sessionPwd);
    }

    public String getZkServerAddress() {
        return zkServerAddress;
    }

    public ZookeeperClient setZkServerAddress(String zkServerAddress) {
        this.zkServerAddress = zkServerAddress;
        return this;
    }

    public Integer getZkClientTimeout() {
        return zkClientTimeout;
    }

    public ZookeeperClient setZkClientTimeout(Integer zkClientTimeout) {
        this.zkClientTimeout = zkClientTimeout;
        return this;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public ZookeeperClient setClosed(boolean closed) {
        isClosed = closed;
        return this;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public ZookeeperClient setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
        return this;
    }

    public Watcher getWatcher() {
        return watcher;
    }

    public ZookeeperClient setWatcher(Watcher watcher) {
        this.watcher = watcher;
        return this;
    }


}
