package com.zhangdy.test.rocketmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangdy.util.HttpUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.flink.table.expressions.E;

import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) throws Exception {
        List<Consumer> allConsumer = findAllConsumer();
        allConsumer = allConsumer.stream().filter(a -> a.count == 0).collect(Collectors.toList());

        allConsumer.forEach(a -> {
            try{
                removeConsumer(a);
            }catch (Exception e) {
                e.printStackTrace();
            }


        });

    }

    public static void removeConsumer(Consumer consumer) throws Exception{
        String url = "http://idc.icctoro.com:5008/consumer/deleteSubGroup.do";
        String req = "{\"groupName\":\"" + consumer.group + "\",\"brokerNameList\":[\"broker-a\"]}";
        String s = HttpUtil.reqPostJson(url, req);
        System.out.println(s);
    }
    public static List<Consumer> findAllConsumer() throws Exception{
        String url = "http://idc.icctoro.com:5008/consumer/groupList.query";
        String s = HttpUtil.reqGetJson(url, null);
        System.out.println(s);
        String data = JSON.parseObject(s).getString("data");
        return JSONArray.parseArray(data, Consumer.class);

    }

    @Getter @Setter
    public static class Consumer{
        private String group;
        private long count;
        private String consumeType;
        private long diffTotal;
    }

}
