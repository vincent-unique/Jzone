package org.trump.vincent.io_socket.aio.tcp.future.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ReadCompletionHandler implements CompletionHandler<Integer,ByteBuffer> {

    private AsynchronousSocketChannel socketChannel;

    public ReadCompletionHandler (AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment){
        attachment.flip();
        byte[] request = new byte[attachment.remaining()];
        try {
            String response = requestHandle(new String(request, "UTF-8"));
            byte[] writeBytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(writeBytes.length);
            writeBuffer.put(writeBytes);
            writeBuffer.flip();
            this.socketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    if(attachment.hasRemaining()){
                        socketChannel.write(attachment);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        socketChannel.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
        }catch (IOException e){

        }
    }

    public String requestHandle(String request){
        String response = "";
        //TODO

        return response;
    }

    @Override
    public void failed(Throwable exe,ByteBuffer attachment){

    }
}
