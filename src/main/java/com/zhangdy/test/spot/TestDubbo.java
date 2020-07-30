package com.zhangdy.test.spot;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.fastjson.JSON;
import com.icc.account.admin.pojo.param.WithdrawVerifyParam;
import com.icc.account.admin.service.AdminWithdrawVerifyApiService;
import com.icc.account.enums.RechargeWithdrawalVerifyStateEnum;
import com.icc.account.pojo.bo.CapitalAccountBO;
import com.icc.account.pojo.param.GetAccountParam;
import com.icc.account.service.CapitalAccountApiService;
import com.icc.instrument.domain.response.InstrumentFullResponse;
import com.icc.instrument.service.InstrumentService;
import com.icc.market.coin.api.KlineApiService;
import com.icc.market.coin.api.MarketApiService;
import com.icc.market.coin.dto.Kline;
import com.icc.match.api.MatchAssistApiService;
import com.icc.match.pojo.bo.HandicapDataBO;
import com.zhangdy.test.dubbo.BaseDubbo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
public class TestDubbo {


    public static void main(String[] args) {
//        Hour24TransactionApiService api = getApi("10.10.23.202:22317", Hour24TransactionApiService.class);
//        Hour24Transaction hourTx = api.get24HourTx("XEM_USDT_ICNCDE_ENCRY");
//        System.out.println(JSON.toJSONString(hourTx));
    }

    @Test
    public void testInstrument() {

        InstrumentService instrumentService = getApi(InstrumentService.class, ServerNode.INSTRUMENT_NODE_DEV);
        InstrumentFullResponse resp = instrumentService.selectFullByCode("BTC_USDT_ICNCDE_ENCRY");
        log.info("{}", JSON.toJSONString(resp));

    }

    @Test
    public void testOrderBook(){
        MatchAssistApiService api = getApi(MatchAssistApiService.class, ServerNode.MATCH_NODE_DEV);
        HandicapDataBO handicapData = api.getHandicapData(4L);
        log.info("{}", JSON.toJSONString(handicapData));
    }



    @Test
    public void testValuation(){

        MarketApiService apiA = getApi(MarketApiService.class, ServerNode.MARKET_COIN_NODE_TEST_A);
        MarketApiService apiB = getApi(MarketApiService.class, ServerNode.MARKET_COIN_NODE_TEST_B);

//        log.info("{}", apiA.valuation("TESTA", "BTC", new BigDecimal("1")));
//        log.info("{}", apiB.valuation("TESTA", "BTC", new BigDecimal("1")));

        log.info("{}", JSON.toJSONString(apiA.getRateList()));
        log.info("{}", JSON.toJSONString(apiB.getRateList()));


    }

    @Test
    public void testAdminWithdrawVerify(){
        AdminWithdrawVerifyApiService api =   getApi(AdminWithdrawVerifyApiService.class, ServerNode.ACCOUNT_DEV_ADMIN_LOCAL);
        WithdrawVerifyParam param = WithdrawVerifyParam.builder()
                                    .withdrawId(1048022106492420096L)
                                    .verifyUserId(2L)
                                    .remark("test")
                                    .verifyTime(new Date())
                                    .verifyState(RechargeWithdrawalVerifyStateEnum.SUCCESS)
                                    .build();
        try {
            api.withdrawVerify(param);
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testKline(){

        KlineApiService api =   getApi(KlineApiService.class, ServerNode.MARKET_COIN_NODE_DEV);
        List<Kline> klines = api.getKlines("BTC_USDT_ICNCDE_ENCRY", "M1");
        log.info("{}", JSON.toJSONString(klines));
    }


    @Test
    public void testGetAccount(){
        CapitalAccountApiService api =   getApi(CapitalAccountApiService.class, ServerNode.ACCOUNT_DEV_TEST_A);
        GetAccountParam param = GetAccountParam.builder()
                .userId(1005249746310422528L)
                .type(1003)
                .category(10)
                .currencyPair("BTC")
                .exchange("ICC")
                .build();
        CapitalAccountBO capitalAccountBO =  api.getAndOpenCaptialAccount(param);
        log.info("{}", JSON.toJSONString(capitalAccountBO));
    }



    public static <T> T getApi(Class<T> clazz, ServerNode serverNode) {
        String url = "dubbo://".concat(serverNode.node).concat("/").concat(clazz.getName());
        ApplicationConfig application = new ApplicationConfig();
        application.setName("icc-trade-coin-app");
        ReferenceConfig<T> reference = new ReferenceConfig();
        reference.setApplication(application);
        reference.setUrl(url);
        reference.setGroup(serverNode.group);
        reference.setCheck(false);
        reference.setRetries(0);
        reference.setInterface(clazz);
        reference.setVersion(serverNode.version);
        reference.setTimeout(10000);
        return  reference.get();
    }



    @AllArgsConstructor
    enum ServerNode {

        INSTRUMENT_NODE_TEST_A("instrument", "1.0.0", "10.10.23.202:20638"),
        INSTRUMENT_NODE_TEST_B("instrument", "1.0.0", "10.10.23.203:20638"),
        INSTRUMENT_NODE_DEV("instrument", "1.0.0", "10.10.23.207:20638"),

        MARKET_COIN_NODE_TEST_A("market-coin", "1.0.0", "10.10.23.202:22317"),
        MARKET_COIN_NODE_TEST_B("market-coin", "1.0.0", "10.10.23.203:22317"),
        MARKET_COIN_NODE_DEV("market-coin", "1.0.0", "10.10.23.207:22317"),

        TRADE_COIN_NODE_TEST_A("coin", "1.0.0", "10.10.23.202:23807"),
        TRADE_COIN_NODE_TEST_B("coin", "1.0.0", "10.10.23.203:23807"),

        MATCH_NODE_TEST_A("match", "1.0.0", "10.10.23.202:23837"),
        MATCH_NODE_TEST_B("match", "1.0.0", "10.10.23.203:23837"),
        MATCH_NODE_DEV("match", "1.0.0", "10.10.23.207:23837"),

        ACCOUNT_ADMIN_DEV("account-admin", "1.0.1", "10.10.23.207:21807"),
        ACCOUNT_DEV_TEST_A("account", "1.0.1", "10.10.23.202:21807"),
        ACCOUNT_DEV_TEST_B("account", "1.0.1", "10.10.23.203:21807"),
        ACCOUNT_DEV_ADMIN_LOCAL("account-admin", "1.0.1", "192.168.21.199:21807"),

        ;

        String group;
        String version;
        String node;
    }

}
