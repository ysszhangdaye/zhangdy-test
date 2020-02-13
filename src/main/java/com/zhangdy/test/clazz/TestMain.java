package com.zhangdy.test.clazz;

import com.zhangdy.util.IDS;

import java.math.BigDecimal;

public class TestMain {

    public static void main(String[] args) {

        System.out.println(new BigDecimal("0").signum());

        System.out.println(IDS.uniqueID());
        System.out.println(Integer.MAX_VALUE);

    }

}
