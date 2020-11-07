package com.zhangdy.test.thread;

public class TestJoin {


    public static void main(String[] args)throws Exception {
        Thread thread1 = new Thread(() -> System.out.println("A"));
        Thread thread2 = new Thread(() -> System.out.println("B"));
        Thread thread3 = new Thread(() -> System.out.println("C"));

        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();
    }

}
