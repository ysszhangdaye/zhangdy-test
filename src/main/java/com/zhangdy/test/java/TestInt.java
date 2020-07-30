package com.zhangdy.test.java;

import lombok.Getter;
import lombok.Setter;

public class TestInt {

    public static void main(String[] args) {

//        User user = new User();
//        user.setAge(199);
//
//        TestInt t = new TestInt();
//        t.test(user);


        Thread.currentThread().getId();

    }


    public void test(User user){
        user.setAge(200);
    }


    @Getter
    @Setter
    public static class User {
        private Integer age;
    }

}
