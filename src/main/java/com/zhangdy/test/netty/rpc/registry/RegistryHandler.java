package com.zhangdy.test.netty.rpc.registry;

import com.google.common.collect.Maps;
import com.zhangdy.test.netty.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class RegistryHandler extends ChannelInboundHandlerAdapter {


    public static ConcurrentMap<Object, Object> registryMap = Maps.newConcurrentMap();

    private List<String> classNames = new ArrayList<String>();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol)msg;

        //当客户端建立连接时，需要从自定义协议中获取信息，拿到具体的服务和实参
        //使用反射调用
        if(registryMap.containsKey(request.getClazzName())){
            Object clazz = registryMap.get(request.getClazzName());
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParamClasses());
            result = method.invoke(clazz, request.getArgs());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    public RegistryHandler(){
        //完成递归扫描
        this.scannerClass("com.zhangdy.test.netty.rpc.provider");
        this.doRegister();
        System.out.println();
    }

    private void scannerClass(String packageName) {
        URL resource = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(resource.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                this.scannerClass(packageName+ "." + file.getName());
                continue;
            }
            classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
        }

    }

    private void doRegister () {

        if (classNames.isEmpty()) {
            return;
        }

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                Class<?> interfaces = clazz.getInterfaces()[0];

                registryMap.put(interfaces.getName(), clazz.newInstance());

            }catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

}
