package com.zhangdy.test.heavyload;

import java.math.BigDecimal;

public class TestHeavyLoad {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
//        Human man = new Man();
//        Human woman = new Woman();
//        TestHeavyLoad sr = new TestHeavyLoad();
//        sr.sayHello(man);
//        sr.sayHello(woman);

        System.out.println(new BigDecimal("1.01").pow(365));
        System.out.println(new BigDecimal("1.02").pow(365));
        System.out.println(new BigDecimal("0.99").pow(365));

    }


}
