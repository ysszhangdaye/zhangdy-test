package com.zhangdy.test.design.proxy;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserServiceProxy  implements InvocationHandler {
    private UserService service;

    /**
     * 构造方法，给我们要代理的真实对象赋初值
     *
     */
    public UserServiceProxy(UserService service)
    {
        this.service = service;
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("请求方法：" + method.getName() + "，请求参数：" + JSON.toJSONString(args));
        long l = System.currentTimeMillis();


        Object invoke = method.invoke(service, args);

        System.out.println("请求方法：" + method.getName() + "，请求时间：" + (System.currentTimeMillis() - l));

        return invoke;
    }


}
