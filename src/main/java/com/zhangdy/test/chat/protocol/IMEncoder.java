package com.zhangdy.test.chat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;


public class IMEncoder extends MessageToByteEncoder<IMMessage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          IMMessage message, ByteBuf out) throws Exception {
        out.writeBytes(new MessagePack().write(message));
    }
    
    public String encode (IMMessage messgae) {
        if (messgae == null) {
            return "";
        }

        String prex = "[" + messgae.getCmd() + "]" + "[" + messgae.getTime() + "]";
        if(IMP.LOGIN.getCommand().equals(messgae.getCmd()) ||
                IMP.FLOWER.getCommand().equals(messgae.getCmd())){
            prex += ("[" + messgae.getSender() + "][" + messgae.getTerminal() + "]");
        }else if(IMP.CHAT.getCommand().equals(messgae.getCmd())){
            prex += ("[" + messgae.getSender() + "]");
        }else if(IMP.SYSTEM.getCommand().equals(messgae.getCmd())){
            prex += ("[" + messgae.getOnline() + "]");
        }
        if(!(null == messgae.getContent() || "".equals(messgae.getContent()))){
            prex += (" - " + messgae.getContent());
        }
        return prex;
    }
    
}
