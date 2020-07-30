package com.zhangdy.test.dubbo;

import com.alibaba.fastjson.JSON;
import com.icc.market.coin.api.MarketApiService;
import com.icc.market.coin.pojo.bo.InsRateBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class TestMarketAppDubboApi extends BaseDubbo {


    @Test
    public void testRateList(){
        MarketApiService api = getApi(MarketApiService.class, ServerNode.MARKET_COIN_NODE_LOCAL);
        List<InsRateBO> rateList = api.getRateList();
        log.info("{}", JSON.toJSONString(rateList));
    }

}
