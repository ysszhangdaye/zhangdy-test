package com.zhangdy.test.dubbo;

import com.alibaba.fastjson.JSON;
import com.icc.match.api.MatchAssistApiService;
import com.icc.match.pojo.bo.HandicapDataBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestMatchDubboApi  extends BaseDubbo{

    @Test
    public void testOrderBook(){
        MatchAssistApiService api = getApi(MatchAssistApiService.class, ServerNode.MATCH_NODE_TEST_A);
        HandicapDataBO handicapData = api.getHandicapData(1045884167297212416L);
        log.info("{}", JSON.toJSONString(handicapData));
        long bid = handicapData.getBids().firstLongKey();
        long ask = handicapData.getAsks().firstLongKey();
        log.info("bid:{}", bid);
        log.info("ask:{}", ask);

    }

}
