package com.zhangdy.test.dubbo;


import com.icc.match.api.PushMarketApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class TestMarketCoreDubboApi extends BaseDubbo{


    @Test
    public void testPushMarket(){

        PushMarketApiService api = getApi(PushMarketApiService.class, ServerNode.MARKET_CORE_NODE_183);

        try {
            api.pushOrderCanceled(1L, 2L, 1L, (byte)2, 1L,2L);
        }catch (Exception e) {
            e.printStackTrace();;
        }


    }


}
