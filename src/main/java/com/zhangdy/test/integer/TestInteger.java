package com.zhangdy.test.integer;

public class TestInteger {


    public static void main(String[] args) {

        Integer integer = new Integer(100);
        Integer integer1 = new Integer(100);

        System.out.println(integer == integer1);
        System.out.println(integer >= integer1);
        System.out.println(integer < integer1);


    }

}
