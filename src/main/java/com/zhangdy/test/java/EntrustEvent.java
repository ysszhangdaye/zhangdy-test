package com.zhangdy.test.java;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 委托事件
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EntrustEvent implements Serializable {

    //委托订单ID
    private String orderId;
    private String eventType;

    //委托类型
    private String type;

    //方向 （买,卖）
    private String command;

    //限价
    private String price;

    //委托数量
    private String volume;

    //委托额
    private String amount;

    //用户ID
    private String userCode;

    //委托时间
    private Long entrustTime;

    //交易对
    private String variety;

    private long offset;

    public static void main(String[] args) {
        EntrustEvent entrustEvent = EntrustEvent.builder()
                .orderId("test-b-1")
                .eventType("entrust")
                .type("LIMIT")
                .command("B")
                .price("0.01")
                .volume("0.01")
                .userCode("8123")
                .entrustTime(System.currentTimeMillis())
                .variety("ICC_BTC_ETH")
                .build();


        System.out.println(JSON.toJSONString(entrustEvent));
    }
}
