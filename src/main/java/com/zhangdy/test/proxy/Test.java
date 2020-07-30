package com.zhangdy.test.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        IUserService service = new UserServiceImpl();

        UserProxy handler = new UserProxy(service);

        ClassLoader loader = service.getClass().getClassLoader();
        Class[] interfaces = service.getClass().getInterfaces();
        IUserService subject = (IUserService) Proxy.newProxyInstance(loader, interfaces, handler);

        String hello = subject.say("zhangsan");
        System.out.println(hello);
    }

    public static void test1(String... args){
        String s1 = Arrays.asList(args).stream().filter(arg -> arg != null)
                .reduce((s, s2) -> s.concat("_").concat(s2))
                .get();
        System.out.println(s1);
    }


}
