package org.trump.vincent.netty.rest.service;

import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.AsciiString;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by Vincent on 2017/12/21 0021.
 */
public class HealthServerHandler<T> extends ChannelHandlerAdapter {

    public static final AsciiString CONTENT_TYPE = new AsciiString("Content-Type");
    public static final AsciiString CONTENT_LENGTH = new AsciiString("Content-Length");
    public static final AsciiString CONNECTION = new AsciiString("Connection");
    public static final AsciiString KEEP_ALIVE = new AsciiString("keep-alive");

    @Override
    public void channelRead(ChannelHandlerContext handlerContext, Object message) {
        try {
            if (message instanceof FullHttpRequest) {
                FullHttpRequest request = (FullHttpRequest) message;

//            String uri = request.uri();

                if (request.method().equals(HttpMethod.GET)) {
                    doGet(handlerContext, request);
                } else if (request.method().equals(HttpMethod.POST)) {
                    doPost(handlerContext, request);
                } else if (request.method().equals(HttpMethod.PUT)) {
                    doPut(handlerContext, request);
                } else if (request.method().equals(HttpMethod.DELETE)) {
                    doDelete(handlerContext, request);
                }
            }
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(message);
        }
    }

    private void doGet(ChannelHandlerContext context, FullHttpRequest request) {
        JsonObject response = new JsonObject();
        {
            //TODO handle for get request
        }
        ByteBuf resBuffer = Unpooled.wrappedBuffer(response.toString().getBytes());
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, resBuffer);
        ResponseJson(context, request, res);
    }

    private void doPost(ChannelHandlerContext context, FullHttpRequest request) {
        JsonObject response = new JsonObject();
        {
            //TODO handle for post request
        }
        ByteBuf resBuffer = Unpooled.wrappedBuffer(response.toString().getBytes());
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, resBuffer);
        ResponseJson(context, request, res);
    }

    private void doPut(ChannelHandlerContext context, FullHttpRequest request) {
        JsonObject response = new JsonObject();
        {
            //TODO handle for put request
        }
        ByteBuf resBuffer = Unpooled.wrappedBuffer(response.toString().getBytes());
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, resBuffer);
        ResponseJson(context, request, res);
    }

    private void doDelete(ChannelHandlerContext context, FullHttpRequest request) {
        JsonObject response = new JsonObject();
        {
            //TODO handle for delete request
        }
        ByteBuf resBuffer = Unpooled.wrappedBuffer(response.toString().getBytes());
        FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.OK, resBuffer);
        ResponseJson(context, request, res);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext handlerContext) {
        handlerContext.flush();
    }


    /**
     * 响应HTTP的请求
     *
     * @param handlerContext
     * @param req
     * @param response
     */
    private void ResponseJson(ChannelHandlerContext handlerContext, FullHttpRequest req, FullHttpResponse response) {
        boolean keepAlive = HttpHeaderUtil.isKeepAlive(req);

        response.headers().set(CONTENT_TYPE, "text/json");
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());

        if (!keepAlive) {
            handlerContext.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, KEEP_ALIVE);
            handlerContext.write(response);
        }
        // flush the channel
        handlerContext.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext handlerContext, Throwable cause) {
        cause.printStackTrace();
        handlerContext.close();
    }

    /**
     * @param request
     * @return
     */
    private String parseRequest(FullHttpRequest request) {
        ByteBuf buffer = request.content();
        String requestBody = buffer.toString(CharsetUtil.UTF_8);
        return requestBody;
    }
}
