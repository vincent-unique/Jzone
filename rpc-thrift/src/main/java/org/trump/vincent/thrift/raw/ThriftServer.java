package org.trump.vincent.thrift.raw;

import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Vincent on 2017/12/19 0019.
 */

/**
 * @Reference http://www.importnew.com/23211.html
 */
public class ThriftServer {

    private Integer port = 1024;

    public ThriftServer(Integer port){
        if(port!=null && port>0){
            this.port = port;
        }
    }

    public void servicePublish(){
        try {
            TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(this.port);
        }catch (TTransportException e){

        }

    }
}
