package com.zhangdy.test.http;

import lombok.Setter;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;

@Setter
public class RpcWalletHttpClient {

    public static final MediaType JSON_MEDIA_TYPE
            = MediaType.parse("application/json; charset=utf-8");

    private static final Logger log = LoggerFactory.getLogger(RpcWalletHttpClient.class);

    private OkHttpClient httpClient;

    private final String url;

    private final boolean includeRawResponse;

    private Map<String, String> headers;

    public RpcWalletHttpClient(String url, OkHttpClient httpClient, boolean includeRawResponses) {
        this.url = url;
        this.httpClient = httpClient;
        this.includeRawResponse = includeRawResponses;
    }

    private RpcWalletHttpClient(String url, OkHttpClient httpClient) {
        this(url, httpClient, false);
    }

    public RpcWalletHttpClient(String url) {
        this(url, createOkHttpClient());
    }

    public RpcWalletHttpClient(String url, boolean includeRawResponse) {
        this(url, createOkHttpClient(), includeRawResponse);
    }

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        configureLogging(builder);
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(120,TimeUnit.SECONDS );
        return builder.build();
    }


    private static void configureLogging(OkHttpClient.Builder builder) {
        if (log.isDebugEnabled()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(log::debug);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
    }

    protected InputStream performIO(String request) throws IOException {

        RequestBody requestBody = RequestBody.create(JSON_MEDIA_TYPE, request);
        Headers headers = buildHeaders();

        okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();

        okhttp3.Response response = httpClient.newCall(httpRequest).execute();
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            return buildInputStream(responseBody);
        } else {
            return null;
        }

    }

    public static void main(String[] args) throws Exception {
        String url              = "http://10.10.23.230:18332";
        String user             = "dssj";
        String password         = "dssj123";
        RpcWalletHttpClient btcWalletHttpClient = new RpcWalletHttpClient(url);
        Map<String, String> headers = new HashMap<>();
        String credentials = Base64.encodeBase64String((user.concat(":").concat(password)).getBytes());
        headers.put("Authorization", "Basic" + " " + credentials);
        btcWalletHttpClient.setHeaders(headers);
        InputStream inputStream = btcWalletHttpClient.performIO("{\"jsonrpc\":\"1.0\",\"id\":\"23234212312323\",\"method\":\"getblockcount\"}");
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String str = new String(bytes);
        System.out.println(str);


    }

    private InputStream buildInputStream(ResponseBody responseBody) throws IOException {
        InputStream inputStream = responseBody.byteStream();

        if (includeRawResponse) {
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

        } else {
            return inputStream;
        }
    }



    private Headers buildHeaders() {
        return Headers.of(headers);
    }



}