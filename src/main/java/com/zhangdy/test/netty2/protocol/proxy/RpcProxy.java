package com.zhangdy.test.netty2.protocol.proxy;



import com.zhangdy.test.netty2.protocol.InvokerProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProxy {

    public static <T> T create(Class<T> clazz){
        MethodProxyHandler methodProxyHandler = new MethodProxyHandler(clazz);
        Class[] classes = clazz.isInterface() ? new Class[]{clazz} : clazz.getInterfaces();
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), classes, methodProxyHandler);
    }


    private static class MethodProxyHandler implements InvocationHandler {

        private Class<?> clazz;

        public MethodProxyHandler(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Object.class.equals(method.getDeclaringClass())) {
                try {
                    return method.invoke(this, args);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return rpcInvoke(proxy, method, args);
            }

            return null;
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            InvokerProtocol protocol = InvokerProtocol.builder()
                    .clazzName(this.clazz.getName())
                    .methodName(method.getName())
                    .argsClasses(method.getParameterTypes())
                    .args(args)
                    .build();

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();

            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler( new ChannelInitializer<SocketChannel>() {
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();
                                //自定义协议解码器
                                /** 入参有5个，分别解释如下
                                 maxFrameLength：框架的最大长度。如果帧的长度大于此值，则将抛出TooLongFrameException。
                                 lengthFieldOffset：长度字段的偏移量：即对应的长度字段在整个消息数据中得位置
                                 lengthFieldLength：长度字段的长度：如：长度字段是int型表示，那么这个值就是4（long型就是8）
                                 lengthAdjustment：要添加到长度字段值的补偿值
                                 initialBytesToStrip：从解码帧中去除的第一个字节数
                                 */
                                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                //自定义协议编码器
                                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                                //对象参数类型编码器
                                pipeline.addLast("encoder", new ObjectEncoder());
                                //对象参数类型解码器
                                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                                pipeline.addLast("handler",consumerHandler);
                            }
                        });
                ChannelFuture future = bootstrap.connect("localhost", 7081).sync();
                future.channel().writeAndFlush(protocol).sync();
                future.channel().closeFuture().sync();
            }catch (Exception e) {
                e.printStackTrace();
            }
        return null;

        }


    }

}
