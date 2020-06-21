package com.zhangdy.test.concurrent.thread;

public class FirstThread extends Thread{

    @Override
    public void run() {

        System.out.println("first");
    }

    public static void main(String[] args) {

        FirstThread firstThread = new FirstThread();

        firstThread.start();

        System.out.println("end");
    }

}

