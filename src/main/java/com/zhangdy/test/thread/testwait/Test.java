package com.zhangdy.test.thread.testwait;


public class Test {
    public static void main(String[] args) throws Exception{



        int i = 0;


        int a = i++;
        int j = ++i;

        System.out.println(a);
        System.out.println(j);
    }

}
