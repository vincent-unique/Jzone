package org.trump.vincent.io_socket.aio.tcp.future.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

/**
 * AIO — Socket
 * @Reference https://www.ibm.com/developerworks/cn/java/j-lo-nio2/index.html
 */
public class AsynchronousTcpServer {

    private Integer port;

    AsynchronousServerSocketChannel asynServerSocketChannel;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public AsynchronousTcpServer(int port){
        this.port = port;
    }

    public void start(int num){
        try {
            this.asynServerSocketChannel = AsynchronousServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.port);
            this.asynServerSocketChannel.bind(inetSocketAddress);

            doAccept(this.asynServerSocketChannel);
            logger.info("The AsynServer is startting.");
        }catch (IOException e){
            logger.error("Report Exception.",e);
        }
    }


    public void doAccept(AsynchronousServerSocketChannel asynServer){
        asynServer.accept(this,new AcceptCompletionHandler());
    }
}
