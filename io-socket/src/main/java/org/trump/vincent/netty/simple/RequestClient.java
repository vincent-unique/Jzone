package org.trump.vincent.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Vincent on 2017/12/22 0022.
 */
public class RequestClient {

    private final String host;

    private final Integer port;

    public RequestClient(){
        this(80);
    }
    public RequestClient(int port){
        this("127.0.0.1",port);
    }
    public RequestClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    public void request(){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            //1、new BootStrap
            Bootstrap bootstrap = new Bootstrap();
            //2、add event group
            bootstrap.group(workerGroup);
            //3、Instead of NioServerSocketChannel, NioSocketChannel is being used to create a client-side Channel.
            bootstrap.channel(NioSocketChannel.class);
            //4、
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                public void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast();
                }
            });

            ChannelFuture channelFuture = bootstrap.connect().sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Throwable e) {

        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
