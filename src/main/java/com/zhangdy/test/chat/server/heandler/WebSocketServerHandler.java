package com.zhangdy.test.chat.server.heandler;

import com.zhangdy.test.chat.processor.MessageProcessor;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private MessageProcessor processor = new MessageProcessor();


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel client = ctx.channel();
        String addr = processor.getAddress(client);
        log.info("WebSocket Client:" + addr + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        processor.sendMsg(ctx.channel(), msg.text());
    }
}
