package com.zhangdy.rxjava;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Observable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

public class First {


    private static Executor EXECUTOR = new ScheduledThreadPoolExecutor(100, new BasicThreadFactory.Builder().namingPattern("PublishJob-Execute-Pool-%d--").build());
    public static void main(String[] args) {

        Observable net = Observable.create((e) -> {
//            EXECUTOR.scheduleWithFixedDelay(new Thread(() -> {
                try {
                    Request.Builder builder = new Request.Builder()
                            .url("http://dev.mobile.icctoro.com:5003/api/base/instrument/list?type=ENCRY")
                            .get();
                    Request request = builder.build();
                    Call call = new OkHttpClient().newCall(request);
                    Response response = call.execute();
                    e.onNext(response);
                }catch (Exception ex) {
                }
//            }), 0, 3, TimeUnit.SECONDS);
        }).map((response) -> {
            System.out.println(1);
                ResponseBody body = ((Response)response).body();
                if(body!=null){
                    Map map = parseBody(HashMap.class, body);
                    return map;
                }
                return null;
        }).doOnNext((response) -> {
            System.out.println(2);
            System.out.println(JSON.toJSONString(response));
        });

        Observable cache = Observable.create((e) -> {
            try {
                System.out.println(3);
                HashMap map = new HashMap();
                System.out.println(JSON.toJSONString(map));
                e.onNext(map);
//                Request.Builder builder = new Request.Builder()
//                        .url("http://dev.mobile.icctoro.com:5003/api/base/instrument/list?type=CFD")
//                        .get();
//                Request request = builder.build();
//                Call call = new OkHttpClient().newCall(request);
//                Response response = call.execute();
//                e.onNext(response);
//                e.onComplete();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });


//        net.subscribe();
//        cache.subscribe();
        Observable.concat(cache, net)
                .subscribeOn(Schedulers.io())
                .observeOn(new ExecutorScheduler(EXECUTOR))
                .subscribe((e) -> {
                    System.out.println(4);
//                    HashMap map = (HashMap) e;
//                    System.out.println(JSON.toJSONString(map));
                });

    }






    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static <T> T parseBody(Class<T> type, ResponseBody responseBody){
        try {
            InputStream inputStream = responseBody.byteStream();
            return objectMapper.readValue(inputStream, type);
        }catch (Exception e) {
        }
        return null;
    }

    private static InputStream buildInputStream(ResponseBody responseBody) throws IOException {
        InputStream inputStream = responseBody.byteStream();
        if (true) {
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

}
