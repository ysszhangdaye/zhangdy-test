package com.zhangdy.test.thread;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhangdy.util.DateUtil;
import com.zhangdy.util.HttpUtil;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestBinary extends Thread{

    public static void main(String[] args) throws Exception{

        BigDecimal priceRatio = new BigDecimal("0.0621").divide(new BigDecimal("0.0311"), 8, BigDecimal.ROUND_DOWN);
        System.out.println(priceRatio);


    }

    @Override
    public void run() {
        try {
            commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void commit() throws Exception{
        String url = "";

        Map<String, String> headers = Maps.newConcurrentMap();

        Commit param = Commit.builder()
                .amount(new BigDecimal("0.001"))
                .direction(1)
                .price(getRangePrice(9460L, 9902L))
                .productId(2)
                .times(System.currentTimeMillis())
                .build();
        String hHmmss = DateUtil.getDateFormat(new Date(), "HH时mm分ss.sss秒");
        String s = HttpUtil.reqPostJson(url, JSON.toJSONString(param), headers);
        System.out.println(hHmmss + "    ----->   " + s);

    }

    public static BigDecimal getRangePrice(long min , long max) {
        //int randomNum = RANDOM.nextLong((max - min) + 1) + min;
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        return new BigDecimal(rangeLong);
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class Commit {
        private BigDecimal amount;
        private Integer direction;
        private BigDecimal price;
        private Integer productId;
        private Long times;

    }

}
