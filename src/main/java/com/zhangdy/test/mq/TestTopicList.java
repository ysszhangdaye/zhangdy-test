package com.zhangdy.test.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;

public class TestTopicList {


    public static void main(String[] args) throws Exception {

        String url = "http://10.10.23.204:7008/topic/list.query";

        String s = HttpUtil.reqGetJson(url, Maps.newHashMap());


        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray data = jsonObject.getJSONObject("data").getJSONArray("topicList");
        data.forEach(topic -> {
            System.out.println(topic);
        });


    }

}
