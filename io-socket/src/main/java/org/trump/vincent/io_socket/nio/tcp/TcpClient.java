package org.trump.vincent.io_socket.nio.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TcpClient implements Runnable{

    private final String host;

    private final Integer port;

    private volatile boolean working = false;

    private SocketChannel socketChannel;

    private Selector selector;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public TcpClient(String host,int port){
        this.host = host;
        this.port = port;
        try {
            this.selector = Selector.open();
            this.socketChannel = SocketChannel.open();
            this.socketChannel.configureBlocking(false);
        }catch (IOException e){
            logger.error("Report Exception.",e);
            System.exit(1);
        }
    }

    void doRequest(byte[] request)throws IOException{
        InetSocketAddress inetSocketAddress = new InetSocketAddress(this.host,this.port);

        if(this.socketChannel.connect(inetSocketAddress)){
            this.socketChannel.register(selector, SelectionKey.OP_READ);

            doWrite(this.socketChannel,request);
        }else {
            this.socketChannel.register(this.selector,SelectionKey.OP_CONNECT);
        }
    }

    void doWrite(SocketChannel socketChanne,byte[] body)throws IOException{

        if(body != null && body.length>0){
            /**
             * build buffer with direct buffer
             */
//            ByteBuffer writeBuffer = ByteBuffer.allocateDirect(body.length);

            ByteBuffer writeBuffer = ByteBuffer.allocate(body.length);
            writeBuffer.put(body);
            writeBuffer.flip();
            socketChanne.write(writeBuffer);

            if(!writeBuffer.hasRemaining()){
                logger.debug("Request emit end.");
            }
        }
    }
    public void run(){
        this.working = true;
        String request = "hello";
        try {
            doRequest(request.getBytes());
        }catch (IOException e){
            logger.error("Report Exception.",e);
        }

        while (true){
            if(this.working){
                try {
                    this.selector.select(1000);
                    Set<SelectionKey> selectionKeys = this.selector.keys();

                    for (Iterator<SelectionKey> it=selectionKeys.iterator();it.hasNext();){
                        SelectionKey selectionKey = it.next();
                        it.remove();
                        requestHander(selectionKey);
                        if(selectionKey!=null){
                            selectionKey.cancel();
                            if(selectionKey.channel()!=null){
                                selectionKey.channel().close();
                            }
                        }
                    }
                }catch (IOException e){
                    logger.error("Report Exception.",e);
                }
            }
        }
    }

    public void requestHander(SelectionKey selectionKey)throws IOException{

    }
}
