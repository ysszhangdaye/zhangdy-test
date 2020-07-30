package com.zhangdy.test.lock;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.List;

public class LockTest {


    public static void main(String[] args) {


        BigDecimal divide = new BigDecimal(Double.toString(11963.91D)).subtract(new BigDecimal("11963.91")).divide(new BigDecimal("11963.91"), 2, BigDecimal.ROUND_DOWN).abs();

        System.out.println(divide.compareTo(new BigDecimal("0.1")));

    }

}
