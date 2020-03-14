package com.zhangdy.test.thread.testwait;

public class Test {
    public static void main(String[] args) {

        String a = "A_V_V_CV";
        System.out.println(a.substring(0, a.lastIndexOf("_")));

        Object lock = new Object();

        new Thread(()->{
            try {
                System.out.println("线程A-Wait");
                lock.wait();
                System.out.println("线程A被唤醒");
            }catch (Exception e) {

            }
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(5000);
                System.out.println("唤醒线程A");
                lock.notify();
            }catch (Exception e) {

            }
        }).start();;


    }

}
