package org.trump.vincent.io_socket.aio.tcp.future.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AsynchronousTcpClient implements CompletionHandler<Void, AsynchronousTcpClient>, Runnable {

    private String host;
    private Integer port;
    private AsynchronousSocketChannel socketChannel;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public AsynchronousTcpClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            this.socketChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            logger.error("Report Exception.", e);
        }
    }


    public void doConnet() {
        if (this.socketChannel != null) {
            InetSocketAddress socketAddress = new InetSocketAddress(this.host, this.port);
            this.socketChannel.connect(socketAddress, this, this);
        }
    }

    @Override
    public void run() {
        doConnet();
    }

    @Override
    public void completed(Void result, AsynchronousTcpClient attachment) {
        String request = "/hello";
        byte[] resBytes = request.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(resBytes.length);
        writeBuffer.put(resBytes);
        writeBuffer.flip();

        this.socketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (attachment.hasRemaining()) {
                    socketChannel.write(attachment, attachment, this);
                } else {
                    //
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] resBytes = new byte[attachment.remaining()];
                            attachment.get(resBytes);
                            try {
                                String response = new String(resBytes, "UTF-8");
                                /**
                                 * TODO
                                 * some handling for the response
                                 */
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            exc.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });

    }

    @Override
    public void failed(Throwable exe, AsynchronousTcpClient attachment) {
        try {
            this.socketChannel.close();
        } catch (IOException e) {
            logger.error("Report Exception.",e);
        }
    }

}
