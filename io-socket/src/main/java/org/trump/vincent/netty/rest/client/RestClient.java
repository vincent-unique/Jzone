package org.trump.vincent.netty.rest.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpMethod;

/**
 * Created by Vincent on 2017/12/22 0022.
 */

/**
 * @Reference http://netty.io/wiki/user-guide-for-5.x.html
 */
public class RestClient {

    public String call(HttpMethod method,String url,Object request) {

        return null;
    }
}
