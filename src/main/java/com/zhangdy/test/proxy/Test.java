package com.zhangdy.test.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        IUserService service = (UserServiceImpl) userService;

        UserProxy handler = new UserProxy(service);

        ClassLoader loader = service.getClass().getClassLoader();
        Class[] interfaces = service.getClass().getInterfaces();
        IUserService subject = (IUserService) Proxy.newProxyInstance(loader, interfaces, handler);


        userService.service = subject;

//        String hello = subject.say("zhangsan");
//        System.out.println(hello);


        String hello1 = subject.say2("zhangsan");
        System.out.println(hello1);

//        String hello2 = subject.say3("zhangsan");
//        System.out.println(hello2);




    }

    public static void test1(String... args){
        String s1 = Arrays.asList(args).stream().filter(arg -> arg != null)
                .reduce((s, s2) -> s.concat("_").concat(s2))
                .get();
        System.out.println(s1);
    }


}
