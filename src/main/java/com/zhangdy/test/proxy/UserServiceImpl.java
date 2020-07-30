package com.zhangdy.test.proxy;

public class UserServiceImpl implements IUserService{

    @Override
    public String say(String name) {
        return "hello " + name + "！！！";
    }
}
