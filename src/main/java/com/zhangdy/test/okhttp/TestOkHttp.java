package com.zhangdy.test.okhttp;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestOkHttp {
    private static final Logger log = LoggerFactory.getLogger(TestOkHttp.class);

    public static void main(String[] args) throws Exception {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .proxy(Proxy.NO_PROXY)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .addInterceptor(chain -> {
                    Request req = chain.request();
                    Request.Builder b = req.newBuilder();

                    return chain.proceed(b.build());
                });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        OkHttpClient okHttpClient = builder.build();


//        application/json
//        application/json; charset=utf-8

        Map<String, String> headers = Maps.newHashMap();
        headers.put("deviceId", "ffffffff-c369-b973-ffff-ffffca01fdf4");

        String url = "http://localhost:8023/wallet/v1/addWallet";
//        String url = "http://dev.mobile.icctoro.com:5003/api/wallet/v1/addWallet";

        String request = "{\"appWalletId\":\"f82683d4790506e5ea68ee383c85bc5\",\"appWalletName\":\"娜可露露\"}";

        InputStream inputStream = performIO(url, request, headers, okHttpClient);

        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String str = new String(bytes);
        String s = JSON.parseObject(str, String.class);
        System.out.println(s);

    }





    public static InputStream performIO(String reqUrl, String request, Map<String, String> headersMap, OkHttpClient okHttpClient) throws IOException {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), request);
        Headers headers = buildHeaders(headersMap);

        okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
        builder.url(reqUrl)
                .post(requestBody);
        if (headers != null) {
            builder.headers(headers);
        }
        okhttp3.Request httpRequest = builder.build();
        okhttp3.Response response = okHttpClient.newCall(httpRequest).execute();
        ResponseBody responseBody = response.body();

        if (responseBody != null) {
            return buildInputStream(responseBody);
        } else {
            return null;
        }
    }

    private static InputStream buildInputStream(ResponseBody responseBody) throws IOException {
        InputStream inputStream = responseBody.byteStream();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body
        Buffer buffer = source.buffer();
        long size = buffer.size();
        if (size > Integer.MAX_VALUE) {
            throw new UnsupportedOperationException(
                    "Non-integer input buffer size specified: " + size);
        }

        int bufferSize = (int) size;
        BufferedInputStream bufferedinputStream =
                new BufferedInputStream(inputStream, bufferSize);

        bufferedinputStream.mark(inputStream.available());
        return bufferedinputStream;

    }
    private static Headers buildHeaders(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return null;
        }
        return Headers.of(headers);
    }



}
