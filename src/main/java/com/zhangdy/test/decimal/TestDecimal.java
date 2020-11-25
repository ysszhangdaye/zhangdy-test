package com.zhangdy.test.decimal;

import java.math.BigDecimal;

public class TestDecimal {


    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("1.2");
        System.out.println(bigDecimal.scale());

        bigDecimal = new BigDecimal("1.20");
        System.out.println(bigDecimal.scale());

        bigDecimal = new BigDecimal("1.2000");
        System.out.println(bigDecimal.stripTrailingZeros().scale());
        bigDecimal = new BigDecimal("1.0000");
        System.out.println(bigDecimal.stripTrailingZeros().scale());
        bigDecimal = new BigDecimal("1");
        System.out.println(bigDecimal.stripTrailingZeros().scale());

    }

}
