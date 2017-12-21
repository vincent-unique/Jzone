package org.trump.vincent.netty.rest.service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
/**
 * Created by Vincent on 2017/12/21 0021.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslContext;

    public ServerInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    public void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        if (sslContext != null) {
            pipeline.addLast(sslContext.newHandler(channel.alloc()));
        }
        /*HTTP 服务的解码器*/
        pipeline.addLast(new HttpServerCodec());
        /*HTTP 消息的合并处理*/
        pipeline.addLast(new HttpObjectAggregator(2048));
//        pipeline.addLast(new HealthServerHandler()); /*自己写的服务器逻辑处理*/
    }
}
