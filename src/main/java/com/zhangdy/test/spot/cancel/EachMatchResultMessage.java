package com.zhangdy.test.spot.cancel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EachMatchResultMessage {

    //金融工具id
    private Long instrument;
    //撮合时间戳
    private Long timestamp;
    //撮合成交价格(taker 价格)
    private Long price;
    //撮合数量（如果是撤单，则为撤单完成数量）
    private Long executedQuantity;
    //市价买单撮合完成后剩余撤单数量
    private BigDecimal marketBuyCancelQuantity;
    //撮合剩余数量
    private Long remainingQuantity;
    //taker 订单id
    private Long takerOrderId;
    //撮合id
    private Long matchId;
    //maker 订单id
    private Long makerOrderId;
    //撤单原因(如果是撤单委托):1 REQUEST ;2 SYSTEM
    private Integer cancelReason;

    //    LIMIT_BUY(1, "限价买"),//买
    //    LIMIT_SELL(2, "限价卖"),//卖
    //    MARKET_BUY(3, "市价买"),//买
    //    MARKET_SELL(4, "市价卖");//卖
    private Integer sideType;

    //委托撮合类型:MARKET(1, "市价委托"),//市价委托
    //    LIMIT(2, "限价委托"),//限价委托
    //    CANCEL(3, "手动撤单"),//手动撤单
    //    SYSTEM_CANCEL(4,"系统撤单");//针对市价单撤单
    private Integer entrustType;


    //撮合之后的买一价,-1标示不存在买一价
    private long afterMatchBuy1;
    //撮合之后的卖一价, -1标示不存在卖一价
    private long afterMatchSell1;
}
