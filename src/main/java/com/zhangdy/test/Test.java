package com.zhangdy.test;

import com.google.common.collect.Maps;
import com.zhangdy.util.HttpUtil;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

public class Test {


    public static void main(String[] args) throws Exception {

        String url = "http://mobile.icctoro.com/api/comm/activity/order";
        Map<String, String> headerMap = Maps.newConcurrentMap();

        headerMap.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5NTA1NTQxODQ0NTcyNDg3NjkiLCJhdWQiOiJudWxsIiwiaXNzIjoiOTUwNTU0MTg0NDU3MjQ4NzY5IiwiZXhwIjoyMTg4ODAyOTMwLCJpYXQiOjE1ODQwMDI5MzAsImp0aSI6IjE0ZGI4YWNkLTVmZTAtNGU0OS1hNTYwLWU0ZGJhNTQ5ZmU2ZiJ9.E-1fWJK5jogtjvP0C8KaktasnaiVuCWoiVEASea7Va1Na0pAPLy0DGlphiLS0BRfNUbY1_eFf3bYjr716zvscg");
        headerMap.put("Content-Language", "zh-cn");
        String s = HttpUtil.reqPostJson(url, "{}", headerMap);

        System.out.println(s);

        post(url);

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

