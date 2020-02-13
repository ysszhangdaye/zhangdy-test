package com.zhangdy.test.design.pattern.builder;

public class Bottle implements Packing {

    @Override
    public String pack() {
        return "Bottle";
    }
}