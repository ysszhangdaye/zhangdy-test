package com.zhangdy.test.chat.server.heandler;

import com.zhangdy.test.chat.processor.MessageProcessor;
import com.zhangdy.test.chat.protocol.IMMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerminalServerHandler extends SimpleChannelInboundHandler<IMMessage> {

    private MessageProcessor processor = new MessageProcessor();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        log.info("Socket Client: 与客户端断开连接:" + cause.getMessage());
        cause.printStackTrace();
        ctx.close();    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IMMessage message) throws Exception {
        processor.sendMsg(ctx.channel(), message);

    }
}
