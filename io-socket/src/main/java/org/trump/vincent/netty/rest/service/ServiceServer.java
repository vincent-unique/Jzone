package org.trump.vincent.netty.rest.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.File;
import java.security.cert.CertificateException;

/**
 * Created by Vincent on 2017/12/21 0021.
 */

/**
 * @Reference :http://www.jianshu.com/p/0d6503aed8e7
 */
public class ServiceServer {

    final static boolean SSL = System.getProperty("ssl")!=null;
    final Integer port;

    private Logger logger = LoggerFactory.getLogger(getClass());
    public ServiceServer(int port){
        this.port = port;
    }

    public void start(){

        SslContext sslContext = null;

        if(SSL){
            try {
                SelfSignedCertificate selfSignedCertificate = new SelfSignedCertificate();
                File sslFile = selfSignedCertificate.certificate();
                sslContext = SslContext.newClientContext(sslFile);
            }catch (CertificateException e){

            }catch (SSLException e){

            }
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup wokerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);

            bootstrap.group(bossGroup,wokerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ServerInitializer(sslContext))
                    .childHandler(new HealthServerHandler<>());

            Channel channel = bootstrap.bind(this.port)
                    .sync()
                    .channel();

            logger.info("Server[127.0.0.1] starting and listen the port["+ this.port+"]." +
                    " You may access the serivces with [" +(SSL?"https":"http")+"].");
            channel.closeFuture().sync();
        }catch (Throwable e){

        }finally {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }

    }
}
