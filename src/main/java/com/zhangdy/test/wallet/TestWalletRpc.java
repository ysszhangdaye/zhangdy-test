package com.zhangdy.test.wallet;

import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;

import java.util.Map;

public class TestWalletRpc {


    public static void main(String[] args)  throws Exception{
//        testUsdtListAccounts();
        Long h = 1000L;
        t(--h);
    }

    public static void t(long h){
        System.out.println(h);
    }


    public static void testUsdtListAccounts() throws Exception{

        String url = "http://10.10.23.232:18332";

        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", "Basic ZHNzajpkc3NqMTIz");
//        {"id":"1017526992761950208","jsonrpc":"1.0","method":"listaccounts"}
        String req = "{\"id\":\"1017526992761950208\",\"jsonrpc\":\"1.0\",\"method\":\"listaccounts\"}";

        String s = HttpUtil.reqPostString(url, req, headers);
        System.out.println(s);


    }


}
