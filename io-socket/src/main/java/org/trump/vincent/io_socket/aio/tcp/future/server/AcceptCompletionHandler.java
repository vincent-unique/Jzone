package org.trump.vincent.io_socket.aio.tcp.future.server;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AsynchronousTcpServer> {

    public void completed(AsynchronousSocketChannel result, AsynchronousTcpServer attachment){
        attachment.asynServerSocketChannel.accept(attachment,this);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        result.read(readBuffer, readBuffer, new ReadCompletionHandler(result));
    }

    public void failed(Throwable exc, AsynchronousTcpServer attachment){
        exc.printStackTrace();
    }
}
