package com.zhangdy.test.spot;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;
import com.zhangdy.util.IDS;
import com.zhangdy.util.ThreadUtil;
import org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.DoubleStream;

public class TestIccSpotMain {


    public static final String DEV_URL              = "http://dev.mobile.icctoro.com:5003/api/";
    public static final String ENTRUST              = "coin/entrust";
    public static final String CANCEL               = "coin/cancel";
    public static final String ENTRUST_PAGE         = "coin/findEntrustOrdersPage";
    public static final String DEAL_DETAIL_PAGE     = "coin/findOrdersDealDetailPage";

    public static final String token                 = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJudWxsIiwiaGVhZEltZyI6Imh0dHA6Ly9pY2NpbWd0ZXN0Lm9zcy1hcC1zb3V0aGVhc3QtMS5hbGl5dW5jcy5jb20vaW1hZ2VzLzIwMTktMTEtMDcvNmQ0ODFkNzdmMzhhNGZkNGI4NDczYzM5ZDFiMWE5MmMuanBnIiwibmlja05hbWUiOiJtaWxlcyIsImNvdW50cnlDb2RlIjoiY24iLCJpbnZpdGVDb2RlIjoiOXJqYjZtIiwic2V4IjowLCJpc3MiOiI5NTk2MjEwOTEzODE5MTk3NDQiLCJ1c2VyVHlwZSI6MCwiZXhwIjoxNTk2MDEyNDU4LCJpYXQiOjE1OTU0MDc2NTgsImVtYWlsIjoibWlsZXN6aGFuZ2R5QDE2My5jb20iLCJqdGkiOiJmYjAyODEzMS1kMWE5LTQ0Y2ItOWE1YS0yMzM5MWI2MTNlNzYifQ.1GenuUXC1iQj2jsLdGwQMwmmdi74LCFQDc568e6Sv8KhtShc2USNYLWRa4EG1eEmu9zpflNqfbYc2vEsM017IA";
    public static final String dev_token             = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJudWxsIiwiaGVhZEltZyI6Imh0dHA6Ly9pY2NpbWd0ZXN0Lm9zcy1hcC1zb3V0aGVhc3QtMS5hbGl5dW5jcy5jb20vaW1hZ2VzLzIwMTktMTEtMDcvNmQ0ODFkNzdmMzhhNGZkNGI4NDczYzM5ZDFiMWE5MmMuanBnIiwibmlja05hbWUiOiJtaWxlcyIsImNvdW50cnlDb2RlIjoiY24iLCJpbnZpdGVDb2RlIjoiOXJqYjZtIiwic2V4IjowLCJpc3MiOiI5NTk2MjEwOTEzODE5MTk3NDQiLCJ1c2VyVHlwZSI6MCwiZXhwIjoxNTk2MDEyNDU4LCJpYXQiOjE1OTU0MDc2NTgsImVtYWlsIjoibWlsZXN6aGFuZ2R5QDE2My5jb20iLCJqdGkiOiJmYjAyODEzMS1kMWE5LTQ0Y2ItOWE1YS0yMzM5MWI2MTNlNzYifQ.1GenuUXC1iQj2jsLdGwQMwmmdi74LCFQDc568e6Sv8KhtShc2USNYLWRa4EG1eEmu9zpflNqfbYc2vEsM017IA";
    public static final String token1                = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJudWxsIiwiaGVhZEltZyI6Imh0dHBzOi8vaWNjaW1ndGVzdC5vc3MtYXAtc291dGhlYXN0LTEuYWxpeXVuY3MuY29tL2ltYWdlcy8yMDIwLTA1LTA3L2RlOWZlYjhiNGE0ODRjYzVhNzk4MTU3ZmU0OGZjNzUwLnBuZyIsIm5pY2tOYW1lIjoianlzXzEwMTgiLCJjb3VudHJ5Q29kZSI6IlBITCIsImludml0ZUNvZGUiOiJkYnZndHgiLCJzZXgiOjAsImlzcyI6IjEwMDUyNDk3NDYzMTA0MjI1MjgiLCJ1c2VyVHlwZSI6NCwiZXhwIjoyNTkzMzk4NjQyLCJpYXQiOjE1OTMzOTg2NDMsImVtYWlsIjoiMTIzNDU2Nzg5QHFxLmNvbSIsImp0aSI6ImJkNGE4Yjg5LTdkYmMtNDg1Ny1iOWRmLTAyYjM0Yjk5NDdmZSJ9.P7MQZHUo_v1H5l2y6Ejl8ObRdj8SgjlczPRk3YixzpIHbk2FLQM7EKXdmIRvQL0D990SkDqpJLuOXFIITnzNMQ";
    public static final String token2                = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJudWxsIiwiaGVhZEltZyI6bnVsbCwibmlja05hbWUiOiJqeXNfMDEzNyIsImNvdW50cnlDb2RlIjoiUEhMIiwiaW52aXRlQ29kZSI6IjN6Z3pjZiIsInNleCI6MCwiaXNzIjoiMTAwNTMwNDM1OTY3NTkxMjE5MiIsInVzZXJUeXBlIjo0LCJleHAiOjI1OTM0MDgxODMsImlhdCI6MTU5MzQwODE4NCwiZW1haWwiOiIzMzc1MTY3OUBxcS5jb20iLCJqdGkiOiI1OTVjOWM2ZS0wZTZkLTQxNTYtYTc5Ni1jMTY0NzUwOTY1OTcifQ.eD2daBf-l5kwpRT_SVA5UX5mv6TJLdB83vc2xIu1YjvrkTURp0uvqNp700YesKgui585kdN-Ewmkb90LRZGBwg";
    public static final String token3                = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJudWxsIiwiaGVhZEltZyI6bnVsbCwibmlja05hbWUiOiJqeXNfbmdkeSIsImNvdW50cnlDb2RlIjoiY24iLCJpbnZpdGVDb2RlIjoiNWVpYjI2Iiwic2V4IjowLCJpc3MiOiIxMDA1MjQ3MzMzMjY4NjExMDcyIiwidXNlclR5cGUiOjAsImV4cCI6MjU5MTA5MzM5NiwiaWF0IjoxNTkxMDkzMzk3LCJlbWFpbCI6Im1pbGVzemhhbmdkeUAxNjMuY29tIiwianRpIjoiNjlhMzM4MDctZWQwOS00Yjk4LWIzN2EtZjNjMWZjMDU5MWZiIn0.A-32RiZRiCHJ0hXZuEIZ24KEUl3HQLN00LYLGGxG1Hixs7HLoFSLb9MOdO7k07Mjv5zrikJTKna8heq6qW5fJg";
    public static final String token4                = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJudWxsIiwiaGVhZEltZyI6bnVsbCwibmlja05hbWUiOiJqeXNfbmdkeSIsImNvdW50cnlDb2RlIjoiY24iLCJpbnZpdGVDb2RlIjoiNWVpYjI2Iiwic2V4IjowLCJpc3MiOiIxMDA1MjQ3MzMzMjY4NjExMDcyIiwidXNlclR5cGUiOjAsImV4cCI6MjU5MTA5MzM5NiwiaWF0IjoxNTkxMDkzMzk3LCJlbWFpbCI6Im1pbGVzemhhbmdkeUAxNjMuY29tIiwianRpIjoiNjlhMzM4MDctZWQwOS00Yjk4LWIzN2EtZjNjMWZjMDU5MWZiIn0.A-32RiZRiCHJ0hXZuEIZ24KEUl3HQLN00LYLGGxG1Hixs7HLoFSLb9MOdO7k07Mjv5zrikJTKna8heq6qW5fJg";
    public static final String token5                = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJudWxsIiwiaGVhZEltZyI6bnVsbCwibmlja05hbWUiOiJqeXNfbmdkeSIsImNvdW50cnlDb2RlIjoiY24iLCJpbnZpdGVDb2RlIjoiNWVpYjI2Iiwic2V4IjowLCJpc3MiOiIxMDA1MjQ3MzMzMjY4NjExMDcyIiwidXNlclR5cGUiOjAsImV4cCI6MjU5MTA5MzM5NiwiaWF0IjoxNTkxMDkzMzk3LCJlbWFpbCI6Im1pbGVzemhhbmdkeUAxNjMuY29tIiwianRpIjoiNjlhMzM4MDctZWQwOS00Yjk4LWIzN2EtZjNjMWZjMDU5MWZiIn0.A-32RiZRiCHJ0hXZuEIZ24KEUl3HQLN00LYLGGxG1Hixs7HLoFSLb9MOdO7k07Mjv5zrikJTKna8heq6qW5fJg";

    public static final String[] tokens = {token, token1, token2, token3, token4, token5};



    public static final Random random               = new Random();


    public static void entrustLimitBatch(int threadNum, int ordersNum, String code, String token){
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustLimitBuy(code, token);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustLimitSell(code, token);
                    }
                }catch (Exception e) {
                }
            }).start();
        }

    }

    public static void entrustMarketBatch(int threadNum, int ordersNum,String code, String token){
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustMarketBuy(code, token);
                    }
                }catch (Exception e) {
                }
            }).start();
        }
        for (int i=0;i<threadNum;i++) {
            new Thread(()->{
                try {
                    for (int j=0;j<ordersNum;j++) {
                        entrustMarketSell(code, token);
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
//        System.out.println(System.currentTimeMillis());
        List<String> codeList = initCodeList();
//        List<String> codeList = Lists.newArrayList();
//        codeList.add("BTC_USDT_ICNCDE_ENCRY");
//        codeList.add("ETH_USDT_ICNCDE_ENCRY");
//        codeList.add("ETH_BTC_ICNCDE_ENCRY");
//        codeList.add("ETC_ETH_ICNCDE_ENCRY");


//        codeList.stream().forEach(code -> {
//            entrustLimitBatch(2, 50000, code, dev_token);
//            entrustMarketBatch(2, 50000, code, dev_token);
//        });
//
//
//        BigDecimal multiply = new BigDecimal("0.26642789065961985690").multiply(BigDecimal.TEN.pow(16));
//        System.out.println(multiply);

//        entrustLimitBatch(2, 50000, "BTC_USDT_ICNCDE_ENCRY", dev_token);
        entrustLimitBatch(2, 50000, "BTC_USDT_ICNCDE_ENCRY", dev_token);
        entrustLimitBatch(2, 50000, "BTC_USDT_ICNCDE_ENCRY", dev_token);
        entrustMarketBatch(2, 50000, "BTC_USDT_ICNCDE_ENCRY", dev_token);

//
//
//        entrustLimitBatch(1, 50000, "ETH_USDT_ICNCDE_ENCRY", dev_token);
//        entrustMarketBatch(1, 50000, "ETH_USDT_ICNCDE_ENCRY", dev_token);
//
//
//
//        entrustLimitBatch(1, 50000, "ETH_BTC_ICNCDE_ENCRY", dev_token);
//        entrustMarketBatch(1, 50000, "ETH_BTC_ICNCDE_ENCRY", dev_token);
//
//
//
//        entrustLimitBatch(1, 50000, "ETC_ETH_ICNCDE_ENCRY", dev_token);
//        entrustMarketBatch(1, 50000, "ETC_ETH_ICNCDE_ENCRY", dev_token);



//        for (String code : codeList) {
//            entrustLimitBatch(10, 50000, code);
////            entrustMarketBatch(10, 50000, code);
//        }
//        System.out.println(System.currentTimeMillis());




//
//        List<String> codeList1 = Lists.newArrayList();
//        codeList1.add("TESTA_USDT_ICNCDE_ENCRY");
//        codeList1.add("TESTB_USDT_ICNCDE_ENCRY");
//        codeList1.add("TESTC_USDT_ICNCDE_ENCRY");
//        codeList1.add("TESTD_USDT_ICNCDE_ENCRY");
//        codeList1.add("TESTE_USDT_ICNCDE_ENCRY");
//        codeList1.stream().forEach(code -> {
////            entrustLimitBatch(2, 50000, code, token);
//            entrustMarketBatch(2, 50000, code, token);
//        });
//
//        List<String> codeList2 = Lists.newArrayList();
//        codeList2.add("TESTF_USDT_ICNCDE_ENCRY");
//        codeList2.add("TESTG_USDT_ICNCDE_ENCRY");
//        codeList2.add("TESTH_USDT_ICNCDE_ENCRY");
//        codeList2.add("TESTI_USDT_ICNCDE_ENCRY");
//        codeList2.add("TESTJ_USDT_ICNCDE_ENCRY");
//
//        codeList2.stream().forEach(code -> {
////            entrustLimitBatch(2, 50000, code, token1);
//            entrustMarketBatch(2, 50000, code, token1);
//        });

//        List<String> codeList3 = Lists.newArrayList();
//        codeList3.add("LTC_BTC_ICNCDE_ENCRY");
//        codeList3.add("BCH_BTC_ICNCDE_ENCRY");
//        codeList3.add("EOS_ETH_ICNCDE_ENCRY");
//        codeList3.add("LTC_ETH_ICNCDE_ENCRY");
//        codeList3.add("COA_USDT_ICNCDE_ENCRY");
//        codeList3.stream().forEach(code -> {
//            entrustLimitBatch(10, 50000, code, token2);
//            entrustMarketBatch(10, 50000, code, token1);
//        });





    }

    public static List<String> initCodeList(){
        List<String> codeList = Lists.newArrayList();
        codeList.add("BTC_USDT_ICNCDE_ENCRY");
        codeList.add("ETH_USDT_ICNCDE_ENCRY");
        codeList.add("ETC_USDT_ICNCDE_ENCRY");
        codeList.add("ETH_BTC_ICNCDE_ENCRY");
        codeList.add("ETC_BTC_ICNCDE_ENCRY");

        codeList.add("ETC_ETH_ICNCDE_ENCRY");
        codeList.add("EOS_USDT_ICNCDE_ENCRY");
        codeList.add("LTC_USDT_ICNCDE_ENCRY");
        codeList.add("BCH_USDT_ICNCDE_ENCRY");
        codeList.add("EOS_BTC_ICNCDE_ENCRY");

        codeList.add("LTC_BTC_ICNCDE_ENCRY");
        codeList.add("BCH_BTC_ICNCDE_ENCRY");
        codeList.add("EOS_ETH_ICNCDE_ENCRY");
        codeList.add("LTC_ETH_ICNCDE_ENCRY");
        codeList.add("COA_USDT_ICNCDE_ENCRY");

        codeList.add("PAHOO_USDT_ICNCDE_ENCRY");
        codeList.add("GOD_USDT_ICNCDE_ENCRY");
        codeList.add("YQS_USDT_ICNCDE_ENCRY");
        codeList.add("XRP_USDT_ICNCDE_ENCRY");
        codeList.add("BSV_USDT_ICNCDE_ENCRY");
        codeList.add("XMR_USDT_ICNCDE_ENCRY");

        codeList.add("XRP_BTC_ICNCDE_ENCRY");
        codeList.add("BSV_BTC_ICNCDE_ENCRY");
        codeList.add("XMR_BTC_ICNCDE_ENCRY");
        codeList.add("XRP_ETH_ICNCDE_ENCRY");
        codeList.add("XMR_ETH_ICNCDE_ENCRY");
        codeList.add("IONC_USDT_ICNCDE_ENCRY");
        return  codeList;
    }


    public static void entrustLimitBuy(String code, String token) throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code(code)
                .side("B")
                .type("LIMIT")
                .price(buyRandomPrice())
                .qty(randomVolume())
                .build();
        String content = entrust.toFromStr();
        String url = DEV_URL + ENTRUST;
        String resp = HttpUtil.reqPostString(url, content, headers);
        System.out.println("限价买委托--->price" + entrust.getPrice() + " ---> " + resp);
        checkFrequently(resp);
    }
    public static void entrustMarketBuy(String code, String token) throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code(code)
                .side("B")
                .type("MARKET")
                .price(BigDecimal.ZERO)
                .qty(randomMarketBuyVolume())
                .build();
        String content = entrust.toFromStr();
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST,content, headers);
        System.out.println("市价买委托--->" + resp);
        checkFrequently(resp);

    }
    public static void entrustMarketSell(String code, String token) throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code(code)
                .side("S")
                .type("MARKET")
                .price(BigDecimal.ZERO)
                .qty(randomMarketSellVolume())
                .build();
        String content = entrust.toFromStr();
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST,content, headers);
        System.out.println("市价卖委托--->" + resp);
        checkFrequently(resp);
    }
    public static void entrustLimitSell(String code, String token) throws Exception{
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", token);
        headers.put("Source-Site", "pc.jys");
        Entity.Entrust entrust = Entity.Entrust.builder()
                .code(code)
                .side("S")
                .type("LIMIT")
                .price(sellRandomPrice())
                .qty(randomVolume())
                .build();
        String content = entrust.toFromStr();
        String resp = HttpUtil.reqPostString(DEV_URL + ENTRUST,content, headers);
        System.out.println("限价卖委托--->price" + entrust.getPrice() + " ---> " + resp);
        checkFrequently(resp);
    }

    public static BigDecimal sellRandomPrice(){
//        int randomInt = random.nextInt(100);
//        if (randomInt<=0) {
//            return randomPrice();
//        }
//        if (randomInt < 10) {
//            return new BigDecimal(randomInt).multiply(new BigDecimal("1000"));
//        }
//        return new BigDecimal(randomInt).multiply(new BigDecimal("100"));
        int i = RandomUtil.randomInt(50000, 720000);
        return new BigDecimal(i).divide(new BigDecimal(100));
    }

    public static BigDecimal buyRandomPrice(){
//        int randomInt = random.nextInt(100);
//        if (randomInt<=0) {
//            return randomPrice();
//        }
//        if (randomInt < 10) {
//            return new BigDecimal(randomInt).multiply(new BigDecimal("1000"));
//        }
//        return new BigDecimal(randomInt).multiply(new BigDecimal("100"));
        int i = RandomUtil.randomInt(50000, 650000);
//        int i = RandomUtil.randomInt(310000, 330000);

        return new BigDecimal(i).divide(new BigDecimal(100));
    }






    public static BigDecimal randomVolume(){


        int randomInt = random.nextInt(20);
        if (randomInt<=0) {
            return randomVolume();
        }
        return new BigDecimal(randomInt).divide(new BigDecimal("10"));
    }

    public static BigDecimal randomMarketSellVolume(){


        int randomInt = random.nextInt(20);
        if (randomInt<=0) {
            return randomMarketSellVolume();
        }
        return new BigDecimal(randomInt).divide(new BigDecimal("10"));
    }

    public static BigDecimal randomMarketBuyVolume(){
        return new BigDecimal("4000");
//        int randomInt = random.nextInt(20000);
//        if (randomInt<=0) {
//            return randomVolume();
//        }
//        return new BigDecimal(randomInt).divide(new BigDecimal("10"));
    }


    public static void checkFrequently(String resp){
        if (resp.indexOf("isp.operation-frequently") > -1) {
            ThreadUtil.SET_SECONDS(1);
        }
        if (resp.indexOf("The system is busy, please try again later") > -1) {
            ThreadUtil.SET_SECONDS(1);
        }

    }

}
