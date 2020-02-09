package com.zhangdy.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

    private Jedis jedis;// 非切片额客户端连接
    private JedisPool jedisPool;// 非切片连接池

    public RedisClient() {
        initialPool("10.10.11.210", 6380, 0);
        jedis = jedisPool.getResource();
    }

    public RedisClient(String ip, int port, int database) {
        initialPool(ip, port, database);
        jedis = jedisPool.getResource();
    }

    public Jedis getJedis() {
        return this.jedis;
    }

    public RedisClient(String ip, int port, int database, String pwd) {
        initialPool(ip, port, database, pwd);
        jedis = jedisPool.getResource();
    }


    /**
     * 初始化非切片池
     */
    private void initialPool(String ip, int port, int database) {
        initialPool(ip, port, database, null);
    }

    /**
     * 初始化非切片池
     */
    private void initialPool(String ip, int port, int database, String pwd) {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, ip, port, 60000, pwd, database);

    }

}