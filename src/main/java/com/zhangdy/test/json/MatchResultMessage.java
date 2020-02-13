package com.zhangdy.test.json;

import com.alibaba.fastjson.JSON;
import com.zhangdy.util.IDS;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchResultMessage implements Serializable {
//
//    1.price 成交价
//2.takerOrderId taker 订单id
//3.takerQuantity 撮合数量
//4.matchId 撮合id
//5.makerOrderId maker 订单id
    private long price;
    private long quantity;
    private long takerOrderId;
    private long matchId;
    private long makerOrderId;
    private long time;
    private long instrument;
    /** 0-买成交 1-卖成交 */
    private int side;
    /** 买一 */
    private long bid;
    /** 卖一  */
    private long ask;


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);

        MatchResultMessage msg = MatchResultMessage.builder()
                .price(10000000)
                .quantity(1000000000)
                .takerOrderId(3L)
                .makerOrderId(5L)
                .matchId(IDS.uniqueID())
                .time(System.currentTimeMillis())
                .instrument(4L)
                .side(0)
                .bid(1)
                .ask(2)
                .build();
        System.out.println(JSON.toJSONString(msg));
    }

}
