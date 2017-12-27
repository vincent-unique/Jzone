package org.trump.vincent.zookeeper.core;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trump.vincent.zookeeper.watcher.LogWatcher;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vincent on 2017/12/7 0007.
 */
public class ZookeeperHelper {
    private ZookeeperClient client;

    private Logger logger = LoggerFactory.getLogger(ZookeeperHelper.class);

    public ZookeeperHelper(ZookeeperClient zkClient) {
        this.client = zkClient;
    }

    /**
     * validate the avaliability of zookeeper client before all operations as to Zookeeper Client
     * @return
     */
    public boolean preValidate() {
        if (client != null && !client.isClosed()) {
            return true;
        }
        return false;
    }

    /**
     * Creat a new Zookeeper Node to the zookeeper server
     * @param path fullname of the new zookeeper node
     * @param initialzeData
     * @param acls
     * @param createMode
     */
    public void createZNode(String path, byte[] initialzeData, List<ACL> acls, CreateMode createMode) {
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                zooKeeper.create(path, initialzeData, acls, createMode);
            } catch (KeeperException e) {
                logger.error("KeeperException ocurrs in create zookeeper node.");
                e.printStackTrace();
            } catch (InterruptedException e) {
                logger.error("InterruptedException ocurrs in create zookeeper node.");
                e.printStackTrace();
            }
        }
    }

    /**
     * @param path
     * @param initialzeData
     * @param acls
     * @param createMode
     * @param callback
     * @param context
     */
    public void createZNode(String path, byte[] initialzeData, List<ACL> acls, CreateMode createMode, AsyncCallback.StringCallback callback,Object context) {
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                zooKeeper.create(path, initialzeData, acls, createMode,callback,context);
            } catch (final Throwable e) {
                logger.error("Exception ocurrs in creating zookeeper node.",e);
                e.printStackTrace();
            }
        }
    }

    /**
     * validate the existence fo the certain znode
     * @param nodePath
     * @param watched
     * @return
     */
    public Stat existNode(String nodePath,boolean watched){
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                return zooKeeper.exists(nodePath,watched);
            } catch (KeeperException e) {

            } catch (InterruptedException e) {

            }
        }
        return null;
    }

    /**
     * obtain the storage data from the certain znode
     * @param path
     * @param watcher
     * @param stat
     * @return
     */
    public byte[] getNodeData(String path, Watcher watcher, Stat stat) {
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                return zooKeeper.getData(path, watcher, stat);
            } catch (KeeperException e) {

            } catch (InterruptedException e) {

            }
        }
        return null;
    }

    /**
     * delete the certain zookeeper node
     * @param nodePath
     * @return
     */
    public boolean deleteNode(String nodePath){
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                zooKeeper.delete(nodePath,-1);
                return true;
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * set OR update data for the certain zookeeper node
     * @param nodePath
     * @param data
     * @param version
     * @return
     */
    public Stat setNodeData(String nodePath,byte[]data,int version){
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                return zooKeeper.setData(nodePath,data,version);
            } catch (KeeperException e) {

            } catch (InterruptedException e) {

            }
        }
        return null;
    }


    /**
     * find all children znode of the certain node
     * @param path
     * @param watched
     * @return
     */
    public List<String> getChildren(String path, boolean watched) {
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                return zooKeeper.getChildren(path, watched);
            } catch (KeeperException e) {

            } catch (InterruptedException e) {

            }
        }
        return null;
    }


    /**
     * find all children znode of the certain node
     * @param path
     * @param watcher
     * @return
     */
    public List<String> getChildren(String path, Watcher watcher) {
        if (preValidate()) {
            ZooKeeper zooKeeper = client.getZooKeeper();
            try {
                return zooKeeper.getChildren(path, watcher);
            } catch (KeeperException e) {

            } catch (InterruptedException e) {

            }
        }
        return null;
    }

    /**
     * close the zookeeper client
     * @throws IllegalAccessException
     */
    public void closeClient() throws IllegalAccessException{
        if (!preValidate()) {
          throw new IllegalAccessException("You can not close the client without being initilizated.");
        }else {
            try {
                client.getZooKeeper().close();
                client.setClosed(true);
            }catch (InterruptedException e){
                logger.error("Exception occurs in closing the zookeeper client.");
            }
        }
    }


    public static void main(String[] args) throws IOException{
        ZookeeperClient client = new ZookeeperClient("127.0.0.0:2181",5000,new LogWatcher(),true);
        ZookeeperHelper helper = new ZookeeperHelper(client);
        helper.createZNode("/services/dubbo","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        helper.createZNode("/services/dubbo/dst_lock","lock for distributed system".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
        helper.deleteNode("/services/dubbo/dst_lock");
    }
}
