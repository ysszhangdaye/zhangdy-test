package com.zhangdy.test.sentinel;

import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Test {

    public static void main(String[] args)throws Exception{
        getCause();
    }


    public static void getCause() throws Exception{
        String url = "http://localhost:7582/coin/getCoinCause";
        for (int i=0;i<10;i++) {
            new Thread(()->{
                try {
                    Map<String, String> headers = Maps.newConcurrentMap();
                    headers.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTk2MjEwOTEzODE5MTk3NDQiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTU5NjIxMDkxMzgxOTE5NzQ0IiwiZXhwIjoyMTc5MTIwODYxLCJpYXQiOjE1NzQzMjA4NjEsImp0aSI6IjAyN2ZmNTJmLTFmNmUtNGZhYy05OGJiLTE4ZGM0Yzc1NGJmOCJ9._EbhkUpuhwy86gvc1BbJjjbetbw_-da9HCkljHVFJQwmKYMMciUNsuSPIbTjYyiXBcfAqQlrlmOt6Mq3ZZuuXg");
                    String s = HttpUtil.reqGetJson(url, headers);
                    System.out.println(s);
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }).start();
        }


    }


}
