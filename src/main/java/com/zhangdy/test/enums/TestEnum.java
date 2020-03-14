package com.zhangdy.test.enums;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.math.BigDecimal;

public class TestEnum {

    @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
    public static class Balance{
        private String currency;
        private BigDecimal amount = BigDecimal.ZERO;
    }

    public static void main(String[] args) {

        System.out.println(JSON.toJSONString(Balance.builder().currency("BTC").build()));

    }




}
