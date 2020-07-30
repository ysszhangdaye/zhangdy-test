package com.zhangdy.test.sort;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestListSort {


    public static void main(String[] args) {
        List<Trade> list = Lists.newArrayList();
        list.add(new Trade(1L, "1"));
        list.add(new Trade(1L, "1"));
        list.add(new Trade(2L, "1"));
        list.add(new Trade(3L, "1"));
        list.add(new Trade(0L, "1"));
        list.add(new Trade(2L, "1"));
        list.add(new Trade(3L, "1"));
        list.add(new Trade(5L, "1"));
        list.add(new Trade(2L, "1"));
        list.add(new Trade(6L, "1"));
        list.add(new Trade(2L, "1"));
        list.add(new Trade(6L, "1"));
        list.add(new Trade(2L, "1"));
        list.add(new Trade(6L, "1"));
        list.add(new Trade(2L, "1"));
        list.add(new Trade(110L, "1"));
        list.add(new Trade(12L, "1"));
        list.add(new Trade(10L, "1"));
        list.add(new Trade(12L, "1"));
        list.add(new Trade(13L, "1"));
        Collections.sort(list, Comparator.comparing(Trade::getId));

        System.out.println(JSON.toJSONString(list));


        for (Trade t : list) {

            if (t.id.longValue() == 1L){
                System.exit(0);
            }
            System.out.println(t.getId());
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Trade {

        private Long id;
        private String desc;


    }

}
