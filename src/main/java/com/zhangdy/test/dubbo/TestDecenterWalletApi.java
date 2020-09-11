package com.zhangdy.test.dubbo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.icc.decenter.wallet.api.*;
import com.icc.decenter.wallet.pojo.bo.CollectionBO;
import com.icc.decenter.wallet.pojo.bo.ConversionQuoteParamBO;
import com.icc.decenter.wallet.pojo.bo.I18nDictionaryBO;
import com.icc.decenter.wallet.pojo.bo.I18nMetaLanguageBO;
import com.icc.decenter.wallet.pojo.param.ConversionQuoteParamChangeParam;
import com.icc.decenter.wallet.pojo.param.DidiPayCollectionParam;
import com.icc.decenter.wallet.pojo.param.NotNotifyCollectionParam;
import com.infra.tools.IDS;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class TestDecenterWalletApi extends BaseDubbo{


    @Test
    public void testI18nApi(){
        I18nDictionaryApiService api = getApi(I18nDictionaryApiService.class, ServerNode.DECENTER_WALLET_LOCAL);
        List<I18nDictionaryBO> dictionaryList = api.findDictionaryList();
        log.info("{}", JSON.toJSONString(dictionaryList));
    }

    @Test
    public void testI18nLanguageApi(){
        I18nMetaLanguageApiService api = getApi(I18nMetaLanguageApiService.class, ServerNode.DECENTER_WALLET_LOCAL);
        I18nMetaLanguageBO languageMessage = api.getLanguageMessage("1", "zh-cn");
        log.info("{}", JSON.toJSONString(languageMessage));
    }

//    @Test
//    public void test(){
//        ConversionQuoteParamChangeApiService api = getApi(ConversionQuoteParamChangeApiService.class, ServerNode.DECENTER_WALLET_LOCAL2);
//        List<ConversionQuoteParamChangeParam.BasicRate> basicRateList = Lists.newArrayList();
//        List<ConversionQuoteParamChangeParam.CurrencyQtyRatio> currencyQtyRatioList = Lists.newArrayList();
//
//        basicRateList.add(ConversionQuoteParamChangeParam.BasicRate.create("btc/usdt", new BigDecimal("2909")));
//        basicRateList.add(ConversionQuoteParamChangeParam.BasicRate.create("btc/eth", new BigDecimal("909")));
//        basicRateList.add(ConversionQuoteParamChangeParam.BasicRate.create("btc/ltc", new BigDecimal("109")));
//        basicRateList.add(ConversionQuoteParamChangeParam.BasicRate.create("btc/etc", new BigDecimal("3909")));
//
//        currencyQtyRatioList.add(ConversionQuoteParamChangeParam.CurrencyQtyRatio.create("btc", new BigDecimal("0.23")));
//        currencyQtyRatioList.add(ConversionQuoteParamChangeParam.CurrencyQtyRatio.create("eth", new BigDecimal("0.23")));
//        currencyQtyRatioList.add(ConversionQuoteParamChangeParam.CurrencyQtyRatio.create("usdt", new BigDecimal("0.23")));
//        currencyQtyRatioList.add(ConversionQuoteParamChangeParam.CurrencyQtyRatio.create("ltc", new BigDecimal("0.23")));
//        currencyQtyRatioList.add(ConversionQuoteParamChangeParam.CurrencyQtyRatio.create("etc", new BigDecimal("0.23")));
//
//        ConversionQuoteParamChangeParam param = ConversionQuoteParamChangeParam.builder()
//                .basicRateList(basicRateList)
//                .currencyQtyRatioList(currencyQtyRatioList).build();
//
//        api.conversionQuoteParamChange(param);
//
//    }


    @Test
    public void testCollectionBack(){
        CollectionApiService api = getApi(CollectionApiService.class, ServerNode.DECENTER_WALLET_DEV);

        DidiPayCollectionParam param = DidiPayCollectionParam.builder()
                .address("test-address")
                .currency("BTC")
                .amount(new BigDecimal("1.212"))
                .businessNo(IDS.uniqueID().toString())
                .confirmations(2L)
                .status(1)
                .txid(IDS.uniqueID().toString())
                .build();
        api.collection(param);

    }

    @Test
    public void testNotNotifyCollectionList(){
        CollectionApiService api = getApi(CollectionApiService.class, ServerNode.DECENTER_WALLET_LOCAL);
        NotNotifyCollectionParam param = NotNotifyCollectionParam.builder()
                .deviceId("zhangdaye")
                .walletId("zhangdaye")
                .build();

        List<CollectionBO> notNotifyCollectionList = api.findNotNotifyCollectionList(param);
        log.info("{}", JSON.toJSONString(notNotifyCollectionList));


    }

    @Test
    public void testGetConversionQuoteParam(){
        ConversionApiService api = getApi(ConversionApiService.class, ServerNode.DECENTER_WALLET_LOCAL);
        ConversionQuoteParamBO conversionQuoteParam = api.getConversionQuoteParam("BTC/USDT_OMNI");

        log.info("{}", JSON.toJSONString(conversionQuoteParam));


    }


}
