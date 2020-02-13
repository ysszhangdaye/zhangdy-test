package com.zhangdy.test.ordes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestOrder {


    public static void main(String[] args) {

        Comparator<MatchPrice> desc = Comparator.comparing(MatchPrice::getPrice).reversed();
        Comparator<MatchPrice> asc  = Comparator.comparing(MatchPrice::getPrice);



        List<MatchPrice> asks = Lists.newArrayList();
        asks.add(new MatchPrice(new BigDecimal("10"), new BigDecimal("2")));
        asks.add(new MatchPrice(new BigDecimal("20"), new BigDecimal("3")));

        List<MatchPrice> bids = Lists.newArrayList();
        bids.add(new MatchPrice(new BigDecimal("1"), new BigDecimal("2")));
        bids.add(new MatchPrice(new BigDecimal("2"), new BigDecimal("3")));

        MatchOrderBook matchOrderBook = new MatchOrderBook(asks, bids);

        System.out.println(JSON.toJSONString(matchOrderBook));

        Collections.sort(matchOrderBook.getAsks(), asc);
        Collections.sort(matchOrderBook.getBids(), desc);


        System.out.println(JSON.toJSONString(matchOrderBook));

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MatchPrice {
        private BigDecimal price;
        private BigDecimal qty;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MatchOrderBook {
        private List<MatchPrice> asks;
        private List<MatchPrice> bids;
    }
}
