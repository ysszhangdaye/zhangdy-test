package com.zhangdy.test.binary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;
import com.zhangdy.util.RsaUtils;

import java.util.Map;

public class TestUserInfo {
//

    public static void main(String[] args) throws Exception {


        TestUserInfo t = new TestUserInfo();
        t.testBalance();

//
//        String a = "123";
//        System.out.println(a.substring(2));

    }


    public void testGetUserInfo() throws Exception {
        String url = "http://dev.mobile.icctoro.com:5003/api/user/binary/getUserInfo";
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTk2MjEwOTEzODE5MTk3NDQiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTU5NjIxMDkxMzgxOTE5NzQ0IiwiZXhwIjoyMTc5MTIwODYxLCJpYXQiOjE1NzQzMjA4NjEsImp0aSI6IjAyN2ZmNTJmLTFmNmUtNGZhYy05OGJiLTE4ZGM0Yzc1NGJmOCJ9._EbhkUpuhwy86gvc1BbJjjbetbw_-da9HCkljHVFJQwmKYMMciUNsuSPIbTjYyiXBcfAqQlrlmOt6Mq3ZZuuXg");
        String s = HttpUtil.reqGetJson(url, headers);
        System.out.println(s);
        JSONObject parse = JSON.parseObject(s);
        String string = parse.getString("data");
        byte[] bytes = RsaUtils.decryptByPrivateKey(RsaUtils.toByteArray(string), "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ8LtKhy4Q6AgR5NSGX6/AADKIu9wdBF33vLqoy6SiptpgV8t6n6839DlbGBU5SVak56OdvuRedWC1qBY6B6S6/KPjb7LwC8Up2RByebMcLCIYRMiAhwOfX4G0S1STOnEIh3JQczTS/eRGaV3/teIfBSVPRCXcXM5S7GpqJBhaHjAgMBAAECgYAcxHNC3LSUeO3h2zyzJXibT/bvf70kvN61d5s7pR5xGjAjYfGej0Ony0OyPaAuifAWjckVXL3MICYhkrHAfx5dPc2v8yWVZRmCbi/PILOfzAKDFAcloCHTuBps11NXUP65lf2TdEq6t283o0IvBd7CKLgY25AjyJIhWWxpB35dAQJBAOiYeRON1OIvjYkmIVOaod6rKJ6vvKSKyDeaZ/DIU0t/JkZIg5hQ1ZuEELKKl33brn7g1jve5hc185A5RSa8eaMCQQCvDKGCcL29kUGJGtSY/ckWNNr0aoJR2dsVaoL5+K14VT9czlDbgsm8d+EK/OIgKQ8ABbosIzvz7SCdqWQalTrBAkEAmXycUUID3CqDHKDz1wawkI6j5GuVcYM/cinLM0IObUB/kluhsy6Mdu5kUl1QcLY55PIbjTCe52wryN1x+4jePwJBAJUeiHPLQQe9NvvqHFOcVnIRYri2BwBdOxH6Y0s4+eW5kTmpFB57QwnHgbFPjf5hm8KkHl29QjRgu9kzVPCEUcECQDG5L067rbCw0jFCX02wb70ldWmDqzRR02WuQ/+T+cT0/zEgqc+g+TbvomX1lqY+cbGs1WVNiWTZemA/an+Tw1o=");
        System.out.println(new String(bytes));


    }

    public void testBalance() throws Exception {
//        /api/third/balance

        String url = "http://18.162.115.163:1302/api/third/balance";
        Map<String, String> headers = Maps.newConcurrentMap();
        headers.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTk2MjEwOTEzODE5MTk3NDQiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTU5NjIxMDkxMzgxOTE5NzQ0IiwiZXhwIjoyMTc5MTIwODYxLCJpYXQiOjE1NzQzMjA4NjEsImp0aSI6IjAyN2ZmNTJmLTFmNmUtNGZhYy05OGJiLTE4ZGM0Yzc1NGJmOCJ9._EbhkUpuhwy86gvc1BbJjjbetbw_-da9HCkljHVFJQwmKYMMciUNsuSPIbTjYyiXBcfAqQlrlmOt6Mq3ZZuuXg");


        Map<String, String> params = Maps.newConcurrentMap();
        params.put("currency", "BTC");

        url = url.concat("?currency=BTC");
        String s = HttpUtil.reqGetJson(url, headers);

        System.out.println(s);
    }


}
