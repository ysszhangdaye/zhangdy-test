package com.zhangdy.test.cache;

import com.google.common.cache.Cache;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * @author 22
 */
public abstract class AbstractGoogleCache<K, V> {


    protected  Cache<K, V> CACHE = null;

    /**
     * 初始化
     */
    public abstract void init();


    /**
     * 加载
     * @param key
     * @param <V>
     * @return
     */
    public abstract <V> V load(K key);

    /**
     * 重新加载
     * @param key
     * @param oldValue
     * @return
     */
    public abstract ListenableFuture<V> reload(K key, V oldValue) ;


}
