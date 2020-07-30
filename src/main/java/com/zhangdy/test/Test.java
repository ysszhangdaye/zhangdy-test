package com.zhangdy.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zhangdy.util.HttpUtil;
import com.zhangdy.util.IDS;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Test {


    public static void main(String[] args) throws Exception {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);

        for (int i = 0; i < list.size(); i++) {

            Integer integer = list.get(i);
            System.out.println(i + "  " +integer);

            if (integer == 3) {
                list.remove(integer);
                i--;

            }
        }
        System.out.println(JSON.toJSONString(list));


//        Iterator<Integer> iterator = list.iterator();
//        while(iterator.hasNext()){
//            Integer integer = iterator.next();
//            System.out.println(integer);
//            if(integer==3) {
//                iterator.remove();   //注意这个地方
//            }
//        }

//        TreeSet<Long> longSet = Sets.newTreeSet();
//
//
//        longSet.add(1L);
//        System.out.println(longSet.first());
//        System.out.println(longSet.last());
//        System.out.println(longSet.pollFirst());
//        System.out.println(JSON.toJSONString(longSet));
//        System.out.println(IDS.uniqueID());
//        List<String> list = Lists.newArrayList();
//
//
//        list.add("a1");
//        list.add("a2");
//        list.add("a3");
//        list.add("a4");
//        list.add("a5");
//        list.add("a6");
//        list.add("a7");
//        list.add("a8");
//        list.add("a9");
//        list.add("a10");
//        list.add("a11");
//        list.add("a12");
//
//        for (String a : list) {
//            System.out.println(a);
//        }
//
//        Collections.reverse(list);
//        for (String a : list) {
//            System.out.println(a);
//        }
//



//        String url = "http://mobile.icctoro.com/api/comm/activity/order";
//        Map<String, String> headerMap = Maps.newConcurrentMap();
//
//        headerMap.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTA1NTQxODQ0NTcyNDg3NjkiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTUwNTU0MTg0NDU3MjQ4NzY5IiwiZXhwIjoyMTg4ODAyOTMwLCJpYXQiOjE1ODQwMDI5MzAsImp0aSI6IjE0ZGI4YWNkLTVmZTAtNGU0OS1hNTYwLWU0ZGJhNTQ5ZmU2ZiJ9.E-1fWJK5jogtjvP0C8KaktasnaiVuCWoiVEASea7Va1Na0pAPLy0DGlphiLS0BRfNUbY1_eFf3bYjr716zvscg");
//        headerMap.put("Content-Language", "zh-cn");
//        String s = HttpUtil.reqPostJson(url, "{}", headerMap);
//
//        System.out.println(s);
//
//        post(url);

    }


    public static String post(String url) throws Exception {
        String result = null;


        String  contentType =  "application/json";
        String charset =  "UTF-8";

        String entityContent = "{}";
        PostMethod post = null;
        try {
            post = new PostMethod(url);
            RequestEntity requestEntity = new StringRequestEntity(entityContent, contentType, charset);
            post.setRequestEntity(requestEntity);
            Map<String, String> headerMap = Maps.newConcurrentMap();
            headerMap.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTA1NTQxODQ0NTcyNDg3NjkiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTUwNTU0MTg0NDU3MjQ4NzY5IiwiZXhwIjoyMTg4ODAyOTMwLCJpYXQiOjE1ODQwMDI5MzAsImp0aSI6IjE0ZGI4YWNkLTVmZTAtNGU0OS1hNTYwLWU0ZGJhNTQ5ZmU2ZiJ9.E-1fWJK5jogtjvP0C8KaktasnaiVuCWoiVEASea7Va1Na0pAPLy0DGlphiLS0BRfNUbY1_eFf3bYjr716zvscg");
            headerMap.put("Content-Language", "zh-cn");

            if (null != headerMap && !headerMap.isEmpty()) {
                Set<String> keySet = headerMap.keySet();
                for (String key : keySet) {
                    post.setRequestHeader(key, headerMap.get(key));
                }
            }
            HttpClient client = new HttpClient();
//            client.getParams().setParameter("http.protocol.content-charset", charset);
//            client.getHttpConnectionManager().getParams().setConnectionTimeout(120000);
//            client.getHttpConnectionManager().getParams().setSoTimeout(120000);

            int resCode = client.executeMethod(post);
//            if (resCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream(), charset));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp = "";
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                result = StringUtils.trim(resBuffer.toString());
//            }
            String str=post.getResponseBodyAsString();
            System.out.println(resCode + " ---> " + str);


            System.out.println(resCode + " ---> " + result);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            if (null != post) {
                post.releaseConnection();
            }
        }
        return result;
    }

}

