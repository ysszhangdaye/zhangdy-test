package com.zhangdy.test.dubbo;


import com.alibaba.fastjson.JSON;
import com.icc.instrument.domain.request.InstrumentQueryRequest;
import com.icc.instrument.domain.response.InstrumentResponse;
import com.icc.instrument.service.InstrumentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

@Slf4j
public class TestInstrumentDubboApi extends BaseDubbo{



    @Test
    public void testInstrumentList(){

        InstrumentService api = getApi(InstrumentService.class, ServerNode.INSTRUMENT_NODE_TEST_A);
        List<InstrumentResponse> instrumentResponses = api.selectListByParams(new InstrumentQueryRequest());
        log.info("{}", JSON.toJSONString(instrumentResponses));


    }


}
