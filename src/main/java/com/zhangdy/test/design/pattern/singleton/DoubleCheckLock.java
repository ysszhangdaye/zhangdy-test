package com.zhangdy.test.design.pattern.singleton;

import java.util.Random;

public class DoubleCheckLock {
    private int someField;
    private static  DoubleCheckLock instance;
    private DoubleCheckLock() {
        this.someField = new Random().nextInt(200)+1;         // (1)
    }

    public static DoubleCheckLock getInstance() {
        if  (instance ==  null ) {
            synchronized  (DoubleCheckLock. class ) {
                if  (instance ==  null ) {
                    instance = new  DoubleCheckLock();
                }
            }
        }
        return  instance;
    }
    public int getSomeField() {
        return this.someField;                                // (7)
    }


    public static void main(String[] args) {
        Thread thread2 = new Thread(() -> {

        });
        Thread thread1 = new Thread(() -> {

            DoubleCheckLock.getInstance();

        });



        thread2.start();;
        thread1.start();;


    }


}
