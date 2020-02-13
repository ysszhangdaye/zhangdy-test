package com.zhangdy.test.heap;

import com.google.common.collect.Lists;
import com.zhangdy.test.java.User;

import java.util.List;

public class TestHeapMain {

    public static void main(String[] args) {

        List<User> list = Lists.newArrayList();

        while(true){

            list.add(new User());
            try {
                Thread.sleep(50L);
            }catch (Exception e) {

            }

        }
    }

}
