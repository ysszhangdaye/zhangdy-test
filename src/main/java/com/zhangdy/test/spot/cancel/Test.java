package com.zhangdy.test.spot.cancel;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

public class Test {

    public static void main(String[] args) {
//
//        builder(1021987471801073664L, 117064000000L, 1040770055182827520L, 1040770054664605696L, 175823L, 1, 810000L);
//        builder(1021987471801073664L, 61668000000L, 1040463315698794496L, 1040463315207573504L, 175200L, 2, 790000L);
//        builder(1021987471801073664L, 100358000000L, 1040758408013533184L, 1040758407533510656L, 175777L, 2, 810000L);
        builder(1029468892262580224L, 111632170000L, 1040366844030828544L, 1040366843586232320L, 30850L, 1, 8330000L);
        builder(1029468892262580224L, 124580390000L, 1040464568335585280L, 1040464567807102976L, 31151L, 2, 8290000L);
        builder(1029468892262580224L, 116706830000L, 1040820180022280192L, 1040820179441143808L, 31823L, 2, 5440000L);



    }

    public static void builder(long instrument, long executedQuantity, long takerOrderId, long makerOrderId, long matchId, int sideType, long price){
        EachMatchResultMessage message = EachMatchResultMessage.builder()
                .instrument(instrument)
                .timestamp(System.currentTimeMillis())
                .executedQuantity(executedQuantity)
                .takerOrderId(takerOrderId)
                .makerOrderId(makerOrderId)
                .matchId(matchId)
                .cancelReason(1)
                .sideType(sideType)
                .entrustType(1)
                .afterMatchBuy1(0)
                .afterMatchSell1(0)
                .price(price)
                .build();
        System.out.println(JSON.toJSONString(message));
    }


}
