package com.zhangdy.test.concurrent.thread;

public class SecondThread {

    public  static int count = 0;

    public static synchronized void incr(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SecondThread.count = SecondThread.count + 1;
    }

    public static void main(String[] args) {

        for (int i=0;i<1000;i++) {
            new Thread(()-> SecondThread.incr()).start();

        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(SecondThread.count);

    }
}
