package org.trump.vincent.netty.rest.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http2.HttpUtil;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.OK;

/**
 * Created by Vincent on 2017/12/21 0021.
 */
public class HealthServerHandler<T> extends ChannelInboundHandlerAdapter {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONNECTION = "Connection";
    public static final String KEEP_ALIVE = "keep-alive";

    @Override
    public void channelRead(ChannelHandlerContext handlerContext, Object message) {
        if(message instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) message;

            String uri = request.uri();
            if (request.method().equals(HttpMethod.GET)) {
                doGet(handlerContext,request);
            }else if(request.method().equals(HttpMethod.POST)){
                doPost(handlerContext,request);
            }else if(request.method().equals(HttpMethod.PUT)) {
                doPut(handlerContext,request);
            }else if(request.method().equals(HttpMethod.DELETE)) {
                doDelete(handlerContext,request);
            }
        }
    }

    private void doGet(ChannelHandlerContext context, FullHttpRequest request) {
        // TODO
        JsonObject response = new JsonObject();
        Gson gson = new Gson();
        JsonObject requestJson = gson.fromJson(gson.toJson(request),JsonObject.class);

        ResponseJson(context,request,response.getAsString());
    }

    private void doPost(ChannelHandlerContext context,FullHttpRequest request) {
        //TODO
    }

    private void doPut(ChannelHandlerContext context,FullHttpRequest request) {
        //TODO
    }

    private void doDelete(ChannelHandlerContext context,FullHttpRequest request) {
        //TODO
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }


    /**
     * 响应HTTP的请求
     *
     * @param ctx
     * @param req
     * @param jsonStr
     */
    private void ResponseJson(ChannelHandlerContext ctx, FullHttpRequest req, String jsonStr) {

        boolean keepAlive = HttpUtil. (req);
        byte[] jsonByteByte = jsonStr.getBytes();
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(jsonByteByte));
        response.headers().set(CONTENT_TYPE, "text/json");
        response.headers().setInt(CONTENT_LENGTH, response.content().readableBytes());

        if (!keepAlive) {
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(CONNECTION, KEEP_ALIVE);
            ctx.write(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 获取请求的内容
     *
     * @param request
     * @return
     */
    private String parseJosnRequest(FullHttpRequest request) {
        ByteBuf jsonBuf = request.content();
        String jsonStr = jsonBuf.toString(CharsetUtil.UTF_8);
        return jsonStr;
    }
}
}
