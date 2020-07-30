package com.zhangdy.test.dubbo;

import com.alibaba.fastjson.JSON;
import com.icc.account.enums.AccountTypeEnum;
import com.icc.framework.common.util.WebUtils;
import com.icc.trade.coin.api.CapitalDetailApiService;
import com.icc.trade.coin.api.CoinOrdersApiService;
import com.icc.trade.coin.pojo.bo.CoinCapitalDetailBO;
import com.icc.trade.coin.pojo.bo.CoinCapitalDetailListBO;
import com.icc.trade.coin.pojo.bo.CoinCurrencyCapitalDetailBO;
import com.icc.trade.coin.pojo.param.CoinCapitalDetailListParam;
import com.icc.trade.domain.Order;
import com.icc.trade.enums.Command;
import com.icc.trade.enums.OrderType;
import com.icc.trade.enums.Source;
import com.icc.trade.service.OrderPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class TestTradeDubboApi extends BaseDubbo{



    @Test
    public void testReentrust(){
        CoinOrdersApiService api = getApi(CoinOrdersApiService.class, ServerNode.TRADE_COIN_NODE_LOCAL);
    }


    @Test
    public void testEntrust(){
        OrderPlaceService api = getApi(OrderPlaceService.class, ServerNode.TRADE_COIN_NODE_LOCAL);
        String code="BTC_USDT_ICNCDE_ENCRY";
        Long userId= 959621091381919744L;
        Source source = Source.API;
        Command side = Command.S;
        OrderType type= OrderType.LIMIT;
        BigDecimal price= new BigDecimal("5000");
        BigDecimal qty= new BigDecimal("1");
        Order order = new Order(code, null, null, userId,
                source, side, type,
                price, qty, 0, false);
        api.place(order);

    }

    @Test
    public void testCapitalDetailList(){
        CapitalDetailApiService api = getApi(CapitalDetailApiService.class, ServerNode.TRADE_COIN_NODE_LOCAL);

        CoinCapitalDetailListParam param = CoinCapitalDetailListParam
                .builder()
                .userId(959621091381919744L)
                .build();
        List<CoinCapitalDetailListBO> coinCapitalDetailList = api.findCoinCapitalDetailList(param);
        log.info("{}", JSON.toJSONString(coinCapitalDetailList));
    }

    @Test
    public void testCapitalDetail(){
        CapitalDetailApiService api = getApi(CapitalDetailApiService.class, ServerNode.TRADE_COIN_NODE_LOCAL);
        CoinCapitalDetailBO btc_usdt_icncde_encry = api.getCoinCapitalDetailByCode(959621091381919744L, "BTC_USDT_ICNCDE_ENCRY");
        log.info("{}", JSON.toJSONString(btc_usdt_icncde_encry));
    }

    @Test
     public void testCapitalDetailByCurrency(){
        CapitalDetailApiService api = getApi(CapitalDetailApiService.class, ServerNode.TRADE_COIN_NODE_LOCAL);
        CoinCurrencyCapitalDetailBO btc = api.getCoinCapitalDetailByCurrency(959621091381919744L, "USDT");
        log.info("{}", JSON.toJSONString(btc));
     }



}
