package com.zhangdy.test.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

public class CacheTest {

    public static void main(String[] args) {

        new AbstractGoogleCache<String, Boolean>() {
            @Override
            public void init() {
                CACHE = CacheBuilder.newBuilder()
                        .maximumSize(20)
                        .concurrencyLevel(20)
                        .refreshAfterWrite(3, TimeUnit.MINUTES)
                        .build(new CacheLoader<String, Boolean>() {
                            @Override
                            public Boolean load(String key) throws Exception {
                                return null;
                            }

                            @Override
                            public ListenableFuture<Boolean> reload(String key, Boolean oldValue) throws Exception {
                                return null;
                            }
                        });
            }

            @Override
            public <V> V load(String key) {

                return null;
            }

            @Override
            public ListenableFuture<Boolean> reload(String key, Boolean oldValue) {
                return null;
            }
        };


    }

}
