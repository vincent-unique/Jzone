package org.trump.vincent.zookeeper;

import com.google.gson.Gson;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.trump.vincent.zookeeper.core.ZookeeperClient;
import org.trump.vincent.zookeeper.core.ZookeeperHelper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Vincent on 2017/12/7 0007.
 */
public class Application {
    public static final String ZK_CONNECTIONS = "127.0.0.1:12181,127.0.0.1:12182,127.0.0.1:12183";
    public static void showNode(String path){
        try {
            ZookeeperClient zookeeperClient = new ZookeeperClient(ZK_CONNECTIONS, 500);
            ZookeeperHelper zkHelper = new ZookeeperHelper(zookeeperClient);
            /**
             * show data of the node
             */
            byte[] nodeDatas = zkHelper.getNodeData(path,null,null);
            if(nodeDatas!=null&&nodeDatas.length>0){
                String data = new String(nodeDatas,"utf-8");
                System.out.println("Data of the node["+path+"] :"+data);
            }

            /**
             * show all child node of the node
             */
            List<String> cNodes = zkHelper.getChildren(path,true);
            if(cNodes!=null&&cNodes.size()>0){
                System.out.println("Children of the node["+path+"] :"+new Gson().toJson(cNodes));
            }
        }catch (IOException e){

        }
    }

    public static void createZNode(String fullName) {
        try {
            ZookeeperClient zookeeperClient = new ZookeeperClient(ZK_CONNECTIONS, 500);
            ZookeeperHelper zkHelper = new ZookeeperHelper(zookeeperClient);
            zkHelper.createZNode(fullName,"temp zookeeper node".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }catch (IOException e){

        }
    }

    public static void deleteZNode(String fullName){
        try {
            ZookeeperClient zookeeperClient = new ZookeeperClient(ZK_CONNECTIONS, 500);
            ZookeeperHelper zkHelper = new ZookeeperHelper(zookeeperClient);
            zkHelper.deleteNode(fullName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        createZNode("/temp/space1");
//        createZNode("/temp/space2");
        showNode("/temp");
        deleteZNode("/temp/space2");
        showNode("/temp");
    }
}
