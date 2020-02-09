package com.zhangdy.test.thread;

public class TestJoinRule
{

static int x = 0;
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            x = 100;
        });

        thread.start();
//        System.out.println(x);
        thread.join();
        System.out.println(x);
    }
}
