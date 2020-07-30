package com.zhangdy.test.spot;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ExecutorsConfig {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("reentrust-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 200,
            10L, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void execute(Thread thread){
        pool.execute(thread);
    }

    public static void main(String[] args) {
        ExecutorsConfig.execute(new Thread(()->{
            System.out.println(1);
        }));
    }

}
