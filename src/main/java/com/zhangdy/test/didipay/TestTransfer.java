package com.zhangdy.test.didipay;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;
import com.zhangdy.util.IDS;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

public class TestTransfer {

    private static final String END_POINT = "http://test.www.iccbank.net:7039/";
    private static final String TRANSFER_URL = "v1/mch/assets/transfer";

    public static void main(String[] args) {
        transfer();
//        transferZhouzhiwei();
    }



    public static void transferZhouzhiwei() {

        String currency     = "BTC";
        long fromAccountId = 1062467203295653890L;
        long toAccountId1 = 1062467203295653888L;

        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJuaWNrTmFtZSI6InpoYW5nZHktdGVzdCIsImNvdW50cnlDb2RlIjpudWxsLCJpc3MiOiIxMDYxMTA0MjE1NzQ2MzI2NTI4IiwiZXhwIjoxNTk3OTkwMTYyLCJpYXQiOjE1OTczODUzNjIsImVtYWlsIjoibWlsZXNfemhhbmdkeUBhbGl5dW4uY29tIiwianRpIjoiOWFlNGVjNmEtYTFkZC00YzUzLWI4NDgtNjIxYzk1MDAxZDZhIn0.1Wz-lyxzyVUTJd_M65c5uRu4TLlvIXMTpLI-hYmRkUcT1B2ZZAK5vBmGW9i0BHlYzPJm_OCF0oMCfH6_76AMiA");
        String url  = END_POINT + TRANSFER_URL;
        transfer(url, currency, fromAccountId, toAccountId1, headers);
//        transfer(url, currency, fromAccountId, toAccountId2, headers);
//        transfer(url, currency, toAccountId1, fromAccountId, headers);
//        transfer(url, currency, toAccountId1, toAccountId2, headers);
//        transfer(url, currency, toAccountId2, fromAccountId, headers);
//        transfer(url, currency, toAccountId2, toAccountId1, headers);
//        transfer(url, currency, fromAccountId, fromAccountId, headers);



    }

    public static void transfer() {

        String currency     = "BTC";
        long fromAccountId = 1061105054317326336L;
        long toAccountId1 = 1061344011395444736L;
        long toAccountId2 = 1061105054317326337L;

        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJuaWNrTmFtZSI6InpoYW5nZHktdGVzdCIsImNvdW50cnlDb2RlIjpudWxsLCJpc3MiOiIxMDYxMTA0MjE1NzQ2MzI2NTI4IiwiZXhwIjoxNTk3OTkwMTYyLCJpYXQiOjE1OTczODUzNjIsImVtYWlsIjoibWlsZXNfemhhbmdkeUBhbGl5dW4uY29tIiwianRpIjoiOWFlNGVjNmEtYTFkZC00YzUzLWI4NDgtNjIxYzk1MDAxZDZhIn0.1Wz-lyxzyVUTJd_M65c5uRu4TLlvIXMTpLI-hYmRkUcT1B2ZZAK5vBmGW9i0BHlYzPJm_OCF0oMCfH6_76AMiA");
        String url  = END_POINT + TRANSFER_URL;
        transfer(url, currency, fromAccountId, toAccountId1, headers);
        transfer(url, currency, fromAccountId, toAccountId2, headers);
//        transfer(url, currency, toAccountId1, fromAccountId, headers);
//        transfer(url, currency, toAccountId1, toAccountId2, headers);
//        transfer(url, currency, toAccountId2, fromAccountId, headers);
//        transfer(url, currency, toAccountId2, toAccountId1, headers);
        transfer(url, currency, fromAccountId, fromAccountId, headers);



    }

    private static void transfer(String url, String currency, long fromAccountId, long toAccountId, Map<String, String> headers){
        for (int a = 0; a < 5; a++) {
            new Thread(() -> {
                try {
                    for (;;) {
                        String s = HttpUtil.reqPostJson(url, TransferReq.create(currency, fromAccountId, toAccountId).toJsonReqStr(), headers);
                        System.out.println(s);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TransferReq {
        private BigDecimal amount;
        private String bizId;
        private String currencyCode;
        private long fromAccountId;
        private long toAccountId;

        public String toJsonReqStr(){
            return JSON.toJSONString(this);
        }

        public static TransferReq create(String currencyCode, long fromAccountTypeId,  long toAccountTypeId){
            return TransferReq.builder()
                    .bizId(IDS.uniqueID().toString())
                    .amount(randomVolume())
                    .currencyCode(currencyCode)
                    .fromAccountId(fromAccountTypeId)
                    .toAccountId(toAccountTypeId)
                    .build();
        }

    }

    public static BigDecimal randomVolume(){
        int i = RandomUtil.randomInt(50000, 650000);
        return new BigDecimal(i).divide(new BigDecimal(100000));
    }

}
