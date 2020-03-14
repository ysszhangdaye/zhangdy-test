package com.zhangdy.test.spot;

import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;

public class TestIccSpotMain {


    public static final String DEV_URL              = "http://dev.mobile.icctoro.com:5003/";
    public static final String ENTRUST              = "api/coin/entrust";
    public static final String CANCEL               = "api/coin/cancel";
    public static final String ENTRUST_PAGE         = "api/coin/findEntrustOrdersPage";
    public static final String DEAL_DETAIL_PAGE     = "api/coin/findOrdersDealDetailPage";
    public static final String token                = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTk2MjEwOTEzODE5MTk3NDQiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTU5NjIxMDkxMzgxOTE5NzQ0IiwiZXhwIjoyMTg3NTAzNTI4LCJpYXQiOjE1ODI3MDM1MjgsImp0aSI6Ijc5ZWM3Y2E3LWFkMzgtNGNlNC1iN2FkLWU2NWMwMTRlNTk4NSJ9.85ohVj7-jnGFduUA7L5smz5mVXCXMayMSIYGlbVWy7D4wP4qxAFNXta_DYtjkpoYxGdBq1re1q6eJgThabaA2g";
    public static final String lzqtoken             = " eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTc0MTMzMzg0Mzc4Mjg2MDgiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTU3NDEzMzM4NDM3ODI4NjA4IiwiZXhwIjoyMTg2OTY5MTgzLCJpYXQiOjE1ODIxNjkxODMsImp0aSI6IjRmMGZkMzJiLTA3YjUtNDc5OC1iOGVhLTExMDg1ZjBmYzc2MyJ9.7CBgNXqO3e6Oa_lDJd2TMMx4svt__-SGk5oUI-qbnvpZYaYuQwuF2jgqX3Tcb8KdEnP_pVuGgWrkfTLlexc3Ow";
    public static final Random random               = new Random();

    public static void entrustOnce() throws Exception{
        entrustLimitBuy();
        entrustLimitSell();
        entrustMarketBuy();
        entrustMarketSell();
    }

    public static void entrustBatch(int threadNum, int ordersNum){
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustLimitBuy();
                    }
                }catch (Exception e) {
                }
            }).start();
        }
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustLimitSell();
                    }
                }catch (Exception e) {
                }
            }).start();
        }
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustMarketBuy();
                    }
                }catch (Exception e) {
                }
            }).start();
        }
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustMarketSell();
                    }
                }catch (Exception e) {
                }
            }).start();
        }
    }

    public static void main(String[] args)  throws Exception{
//        entrustOnce();
//        entrustOrdersPage();
//        dealDetail();
        entrustBatch(5, 10);
    }

    public static void dealDetail() throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        String content = "&ordersId=496342044819456";
        String resp = HttpUtil.reqPostString(DEV_URL + DEAL_DETAIL_PAGE,content, headers);
        System.out.println("成交明细 ----> " + resp);
    }

    public static void entrustOrdersPage() throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        String content = "&status=0";
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST_PAGE,content, headers);
        System.out.println("历史委托 ----> " + resp);
    }

    public static void cancel(String ordersId) throws Exception {
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        String content = "&ordersId=" + ordersId;
        String resp = HttpUtil.reqPostString(DEV_URL + CANCEL,content, headers);
        System.out.println("结果--->" + resp);
    }

    public static void entrustLimitBuy() throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code("BTC_USDT_COINBASE_ENCRY")
                .side("B")
                .type("LIMIT")
                .price(randomPrice())
                .qty(randomVolume())
                .build();
        String content = entrust.toFromStr();
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST,content, headers);
        System.out.println("限价买委托--->price" + entrust.getPrice() + " ---> " + resp);
    }
    public static void entrustMarketBuy() throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code("BTC_USDT_COINBASE_ENCRY")
                .side("B")
                .type("MARKET")
                .price(BigDecimal.ZERO)
                .qty(randomVolume().multiply(randomPrice()))
                .build();
        String content = entrust.toFromStr();
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST,content, headers);
        System.out.println("市价买委托--->" + resp);

    }
    public static void entrustMarketSell() throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code("BTC_USDT_COINBASE_ENCRY")
                .side("S")
                .type("MARKET")
                .price(BigDecimal.ZERO)
                .qty(randomVolume())
                .build();
        String content = entrust.toFromStr();
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST,content, headers);
        System.out.println("市价卖委托--->" + resp);
    }
    public static void entrustLimitSell() throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code("BTC_USDT_COINBASE_ENCRY")
                .side("S")
                .type("LIMIT")
                .price(randomPrice())
                .qty(randomVolume())
                .build();
        String content = entrust.toFromStr();
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST,content, headers);
        System.out.println("限价卖委托--->price" + entrust.getPrice() + " ---> " + resp);
    }

    public static BigDecimal randomPrice(){
        int randomInt = random.nextInt(100);
        if (randomInt<=0) {
            return randomPrice();
        }
        if (randomInt < 10) {
            return new BigDecimal(randomInt).multiply(new BigDecimal("1000"));
        }
        return new BigDecimal(randomInt).multiply(new BigDecimal("100"));
    }



    public static BigDecimal randomVolume(){
        int randomInt = random.nextInt(50);
        if (randomInt<=0) {
            return randomVolume();
        }
        return new BigDecimal(randomInt);
    }

}
