package org.trump.vincent.redies.core;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vincent on 2017/12/8 0008.
 */
public class RedisPool {
    public static String REDIS_ADDRESS = "127.0.0.1";
    public static Integer REDIS_PORT;
    public static String AUTH = "admin";
    public static Integer MAX_ACTIVE = 100;
    public static Integer MAX_IDLE = 10;
    public static Integer MAX_WAIT = 1000;
    public static Integer TIMEOUT = 1000;
    public static boolean TEST_ON_BORROW = true;

    private static volatile JedisPool jedisPool = null;
    private static ReentrantLock lock = new ReentrantLock(false);

    public static void initializePool(boolean refreshPool){
        if(refreshPool || jedisPool==null){
            jedisPool = null;
            JedisPoolConfig poolConfig = new JedisPoolConfig();
//            poolConfig.setMaxTotal();
            poolConfig.setMaxIdle(MAX_IDLE);
            poolConfig.setMaxWaitMillis(MAX_WAIT);
            poolConfig.setTestOnBorrow(TEST_ON_BORROW);

            jedisPool = new JedisPool(poolConfig,REDIS_ADDRESS,REDIS_PORT,TIMEOUT,AUTH);
        }
    }

    public static Jedis getRedisClient(){
        synchronized (RedisPool.class){
            if(jedisPool==null){
                initializePool(false);
            }
            return jedisPool.getResource();
        }
    }

    @Deprecated
    public static void releaseConnection(Jedis jedis){
        synchronized (RedisPool.class){
            if(jedisPool!=null){
                jedisPool.returnResource(jedis);
            }
        }
    }
}
