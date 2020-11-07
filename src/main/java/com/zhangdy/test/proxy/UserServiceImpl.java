package com.zhangdy.test.proxy;

public class UserServiceImpl implements IUserService{


    public IUserService service;

    @Override
    public String say(String name) {
        return "say1:hello " + name + "！！！";
    }

    @Override
    public String say2(String name) {

        String say = service.say("text-proxy");
        System.out.println(say);
        return "say2:hello " + name + "！！！";
    }
    @Override
    public String say3(String name) {
        String say = say("text-proxy");
        System.out.println(say);
        return "say2:hello " + name + "！！！";
    }



}
