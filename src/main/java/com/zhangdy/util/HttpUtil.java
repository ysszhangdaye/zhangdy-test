package com.zhangdy.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 网络请求工具类
 */
public class HttpUtil {

    public static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private static HttpUtil instance = new HttpUtil();
    private static HttpClient client;
    private static long startTime = System.currentTimeMillis();
    private static ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {

        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            long keepAlive = super.getKeepAliveDuration(response, context);

            if (keepAlive == -1) {
                keepAlive = 5000;
            }
            return keepAlive;
        }

    };
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000)
            .setConnectionRequestTimeout(20000).build();

    private HttpUtil() {
        client = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(keepAliveStrat).build();
    }

    public static void IdleConnectionMonitor() {

        if (System.currentTimeMillis() - startTime > 30000) {
            startTime = System.currentTimeMillis();
            cm.closeExpiredConnections();
            cm.closeIdleConnections(30, TimeUnit.SECONDS);
        }
    }

    public static HttpUtil getInstance() {
        return instance;
    }

    public HttpClient getHttpClient() {
        return client;
    }

    private HttpPost httpPostMethod(String url) {
        return new HttpPost(url);
    }

    private HttpRequestBase httpGetMethod(String url) {
        return new HttpGet(url);
    }

    public String requestHttpGet(String url_prex, String url, String param) throws IOException {

        IdleConnectionMonitor();
        url = url_prex + url;
        if (param != null && !param.equals("")) {
            url = url + "?" + param;
        }
        HttpRequestBase method = this.httpGetMethod(url);
        method.setConfig(requestConfig);
        HttpResponse response = client.execute(method);
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return "";
        }
        InputStream is = null;
        String responseData = "";
        try {
            is = entity.getContent();
            responseData = IOUtils.toString(is, "UTF-8");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return responseData;
    }

    public String requestHttpPost(String url_prex, String url, Map<String, String> params) throws IOException {

        IdleConnectionMonitor();
        url = url_prex + url;
        HttpPost method = this.httpPostMethod(url);
        List<NameValuePair> valuePairs = this.convertMap2PostParams(params);
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
        method.setEntity(urlEncodedFormEntity);
        method.setConfig(requestConfig);
        HttpResponse response = client.execute(method);
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return "";
        }
        InputStream is = null;
        String responseData = "";
        try {
            is = entity.getContent();
            responseData = IOUtils.toString(is, "UTF-8");
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return responseData;

    }

    private List<NameValuePair> convertMap2PostParams(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        if (keys.isEmpty()) {
            return null;
        }
        int keySize = keys.size();
        List<NameValuePair> data = new LinkedList<NameValuePair>();
        for (int i = 0; i < keySize; i++) {
            String key = keys.get(i);
            String value = params.get(key);
            data.add(new BasicNameValuePair(key, value));
        }
        return data;
    }

    /**
     * @param strUrl
     * @param content
     * @return
     * @throws IOException String
     * @Title: URLPostJsonStream
     * @Description: 流的方式发送Json数据
     * @MethodDesc(value = "", param = {})
     * @author: xuguofei clarkxu33@qq.com
     */
    public static String URLPostJsonStream(String strUrl, String content) throws IOException {
        String result = "";
        URL url = new URL(strUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            con.setUseCaches(false);
            con.setConnectTimeout(100000);
            con.setReadTimeout(100000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.setRequestProperty("Accept", "application/json");// 使用json接收数据
            con.connect();
            try (BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))) {
                bout.write(content);
                bout.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = getStringBufferFormBufferedReader(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return result;
    }

    /**
     * Json参数Post请求
     *
     * @param strUrl  请求地址
     * @param content JSON字符串
     * @return
     * @throws IOException
     */
    public static String reqPostJson(String strUrl, String content) throws Exception {
        String result = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000).build();
        // 使用帮助类HttpClients创建CloseableHttpClient对象.
        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .disableAutomaticRetries().build()) {
            // HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost(strUrl);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            post.setHeader("Accept", "application/json");
            // 组织数据
            StringEntity se = new StringEntity(content);
            // 设置编码格式
            se.setContentEncoding("UTF-8");
            // 设置数据类型
            se.setContentType("application/json");
            // 对于POST请求,把请求体填充进HttpPost实体.
            post.setEntity(se);
            try (CloseableHttpResponse response = httpclient.execute(post)) {
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    result = EntityUtils.toString(response.getEntity());
                } else {
                    throw new RuntimeException(
                            String.format("请求服务器异常,status=%s", response.getStatusLine().getStatusCode()));
                }
            }
        }
        return result;
    }


    public static String reqPostString(String strUrl, String content) throws Exception {
        String result = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000).build();
        // 使用帮助类HttpClients创建CloseableHttpClient对象.
        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .disableAutomaticRetries().build()) {
            // HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost(strUrl);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等数.
            post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            post.setHeader("Accept", "application/json");
            // 组织数据
            StringEntity se = new StringEntity(content);
            // 设置编码格式
            se.setContentEncoding("UTF-8");
            // 设置数据类型
            se.setContentType("application/json");
            // 对于POST请求,把请求体填充进HttpPost实体.
            post.setEntity(se);

            CloseableHttpResponse response = httpclient.execute(post);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                throw new RuntimeException(
                        String.format("请求服务器异常,status=%s", response.getStatusLine().getStatusCode()));
            }

        }
        return result;
    }


    public static String reqPostString(String strUrl, String content, Map<String, String> headers) throws Exception {
        String result = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000).build();
        // 使用帮助类HttpClients创建CloseableHttpClient对象.
        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .disableAutomaticRetries().build()) {
            // HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost(strUrl);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等数.
            post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            post.setHeader("Accept", "application/json");
            if (headers != null && headers.size() > 0) {
                headers.forEach((key, value) -> {
                    post.setHeader(key, value);
                });
            }
            // 组织数据
            StringEntity se = new StringEntity(content);
            // 设置编码格式
            se.setContentEncoding("UTF-8");
            // 设置数据类型
            se.setContentType("application/json");
            // 对于POST请求,把请求体填充进HttpPost实体.
            post.setEntity(se);
            try (CloseableHttpResponse response = httpclient.execute(post)) {
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    result = EntityUtils.toString(response.getEntity());
                } else {
                    throw new RuntimeException(
                            String.format("请求服务器异常,status=%s", response.getStatusLine().getStatusCode()));
                }
            }
        }
        return result;
    }


    private static String getStringBufferFormBufferedReader(BufferedReader in) throws IOException {
        StringBuffer returnStringBuffer = new StringBuffer();
        try {
            char[] tmpbuf = new char[1024];
            int num = in.read(tmpbuf);
            while (num != -1) {
                returnStringBuffer.append(tmpbuf, 0, num);
                num = in.read(tmpbuf);
            }
        } finally {
            in.close();
        }
        return returnStringBuffer.toString();
    }


    public static String reqPostJson(String strUrl, String content, Map<String, String> headers) throws Exception {
        System.out.print("req" + content + "       ------- ");
        String result = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000).build();
        // 使用帮助类HttpClients创建CloseableHttpClient对象.
        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .disableAutomaticRetries().build()) {
            // HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost(strUrl);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            post.setHeader("Accept", "application/json");

            if (headers != null && headers.size() > 0) {
                headers.forEach((key, value) -> {
                    post.setHeader(key, value);
                });
            }

            // 组织数据
            StringEntity se = new StringEntity(content);
            // 设置编码格式
            se.setContentEncoding("UTF-8");
            // 设置数据类型
            se.setContentType("application/json");
            // 对于POST请求,把请求体填充进HttpPost实体.
            post.setEntity(se);
            try (CloseableHttpResponse response = httpclient.execute(post)) {
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    result = EntityUtils.toString(response.getEntity());
                } else {
                    throw new RuntimeException(
                            String.format("请求服务器异常,status=%s", response.getStatusLine().getStatusCode()));
                }
            }
        }
        return result;
    }


    public static String reqGetJson(String strUrl, Map<String, String> headers) throws Exception {
        String result = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000).build();
        // 使用帮助类HttpClients创建CloseableHttpClient对象.
        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .disableAutomaticRetries().build()) {
            // HTTP请求类型创建HttpPost实例
            HttpGet httpGet = new HttpGet(strUrl);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等数.
//			httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpGet.setHeader("Accept", "application/json");

            if (headers != null && headers.size() > 0) {
                headers.forEach((key, value) -> {
                    httpGet.setHeader(key, value);
                });
            }

            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    result = EntityUtils.toString(response.getEntity());
                } else {
                    throw new RuntimeException(
                            String.format("请求服务器异常,status=%s", response.getStatusLine().getStatusCode()));
                }
            }
        }
        return result;
    }

    public static String reqPostXml(String url, String xml, Map<String, String> headers) throws Exception {
        String result = "";
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                .setConnectionRequestTimeout(60000).build();
        // 使用帮助类HttpClients创建CloseableHttpClient对象.
        try {
            CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                    .disableAutomaticRetries().build();
            // HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost(url);
            // 使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等数.
            post.setHeader("Content-Type", "text/html;charset=UTF-8");

            if (headers != null && headers.size() > 0) {
                headers.forEach((key, value) -> {
                    post.setHeader(key, value);
                });
            }

            // 组织数据
            StringEntity se = new StringEntity(xml);
            // 设置编码格式
            se.setContentEncoding("UTF-8");
            // 设置数据类型
            se.setContentType("text/html");
            // 对于POST请求,把请求体填充进HttpPost实体.
            post.setEntity(se);
            CloseableHttpResponse response = httpclient.execute(post);
            System.out.println(JSON.toJSONString(response));
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(response.getEntity());
            } else {
                throw new RuntimeException(
                        String.format("请求服务器异常,status=%s", response.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String xmlPostStream(String strUrl, String content) throws IOException {
        String result = "";
        URL url = new URL(strUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setAllowUserInteraction(false);
            con.setUseCaches(false);
            con.setConnectTimeout(100000);
            con.setReadTimeout(100000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("Accept", "text/xml");// 使用json接收数据
            con.connect();
            try (BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))) {
                bout.write(content);
                bout.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = getStringBufferFormBufferedReader(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return result;
    }



}