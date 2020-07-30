package com.zhangdy.test.spot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sun.misc.Unsafe;

import java.lang.instrument.Instrumentation;
import java.math.BigDecimal;

/**
 * K线
 */
@Getter
@Setter
@NoArgsConstructor
public  class Kline   {

    private Long timestamp;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private BigDecimal volume;

    public static void main(String[] args) {
        Kline kline = new Kline();
    }

}