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
        /**
         * Connect to the socket server.
         * the socketChannel will register to the selecto(OP_READ),if connect successfully with 3 tcps.
         * otherwise (no get the response from the server ),the socketChannel wiil register to the selector(PO_CONNECT)
         * ******************
         * 将连接的建立 和请求数据的发送 抽象为两个过程
         */
        if(this.socketChannel.connect(inetSocketAddress)){
            this.socketChannel.register(selector, SelectionKey.OP_READ);

            doWrite(this.socketChannel,request);
        }else {
            /**
             * 如果socket连接未得到应答（tcp 三次应答完成）
             * 就将channel注册为 connect型，待SelectionKey轮训到该连接就绪，再做后续的请求发送
             */
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
        String request = "/hello";
        try {
            /**
             * Emit the request,firstly
             */
            doRequest(request.getBytes());

        }catch (IOException e){
            logger.error("Report Exception.",e);
        }

        while (true){
            if(this.working){
                try {
                    this.selector.select(1000);
                    Set<SelectionKey> selectionKeys = this.selector.keys();

                    /**
                     * Loop for the SelectionKey
                     */
                    for (Iterator<SelectionKey> it=selectionKeys.iterator();it.hasNext();){
                        SelectionKey selectionKey = it.next();
                        it.remove();
                        requestHandler(selectionKey);

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

    public void requestHandler(SelectionKey selectionKey)throws IOException{
        if(selectionKey.isValid()){
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            /**
             * Request Emitter
             * 对应于，一开始连接未得到响应（finish connect）
             * 轮训 SelectionKey，当类型是连接型时（并连接完成），就发送请求
             */
            if(selectionKey.isConnectable()){
                if(socketChannel.finishConnect()){
                    socketChannel.register(this.selector,SelectionKey.OP_READ);

                    doWrite(socketChannel,"/hello".getBytes());
                }
            }

            /**
             * Response receiver and handler
             */
            if(selectionKey.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readNums = socketChannel.read(readBuffer);
                if(readNums>0){
                    readBuffer.flip();
                    byte[] bufferBytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bufferBytes);
                    String requestBody = new String(bufferBytes,"UTF-8");

                    /**
                     * TODO
                     * following handling for the request body
                     */

                }else if(readNums<0){
                    selectionKey.cancel();
                    socketChannel.close();
                }else ;
            }
        }
    }
}
