package org.trump.vincent.io_socket.nio.tcp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TcpServer {
    private final Integer port;

    private volatile boolean working = false;

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private Logger logger = LoggerFactory.getLogger(getClass());
    public TcpServer(int port){
        this.port = port;
    }

    public void start(){
        this.working = true;
        try {
            this.selector = Selector.open();

            this.serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.port);
            this.serverSocketChannel.bind(inetSocketAddress)
                    .configureBlocking(false);

            this.serverSocketChannel.register(this.selector,SelectionKey.OP_ACCEPT);

            logger.info("The Server is startting and listen the port[ "+this.port+" ].");

            while (true) {

                if (this.working) {
                    this.selector.select(1000);
                    Set<SelectionKey> selectionKeys = this.selector.selectedKeys();

                    for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); ) {

                        SelectionKey key = it.next();
                        it.remove();
                        try {
                            taskHandler(key);

                        } catch (Exception e) {
                            logger.error("Report Exception.", e);
                        } finally {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null)
                                    key.channel().close();
                            }
                        }
                    }
                }

                /**
                 * Close the selector when the worker is stoped.
                 * All channels and pipes will be close registered in the selector when the selector is closed.
                 */
                if (this.selector != null) {
                    this.selector.close();
                }
            }

        }catch (IOException e){
            logger.error("Report Exception.",e);
        }
    }

    public void taskHandler(SelectionKey selectionKey)throws Exception{

        if(selectionKey.isValid()){

            /**
             * acceptable（ServerSocketChannel在selector中注册为OP_ACCEPT）
             * Server SocketChannel
             * 将接收到的Socket连接注册到Selector （Read型），
             * 待SelectionKey轮训到该Socket(Readable),就从中读取请求，并做Response.
             * **********************
             * 即，以不阻塞的方式处理socket请求（轮训到socket连接注册到Selector）；
             * 以防止，连接建立，但是请求的数据未发送（Readable，代表请求发送中）
             */
            if(selectionKey.isAcceptable()){
                ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverChannel.accept();

                socketChannel.configureBlocking(false);
                socketChannel.register(this.selector,SelectionKey.OP_READ);
            }

            /**
             * readable
             * General SocketChannel
             */
            if(selectionKey.isReadable()){
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int readNum = socketChannel.read(byteBuffer);

                if(readNum>0){
                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);

                    String body = new String(bytes,"UTF-8");
                    logger.info("Server receive："+body);

                    String response = requestHandle(body);

                    doWrite(socketChannel,response);

                }else if(readNum<0){
                    selectionKey.cancel();
                    socketChannel.close();
                }else;
            }
        }
    }


    public String requestHandle(String request){
        String response = null;
        //TODO
        return response;
    }

    public void doWrite(SocketChannel socketChannel, String response)throws IOException{
        if(response!=null && response.trim().length()>0){
            byte[] resBytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(resBytes.length);
            writeBuffer.put(resBytes);
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
        }
    }
    public void stop(){
        this.working = false;
    }
}
