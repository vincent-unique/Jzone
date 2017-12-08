package org.trump.vincent.redies.core;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vincent on 2017/12/8 0008.
 */
public class RedisConnection {
    public static String redisAddress = "127.0.0.1";
    public static Integer redisPort;
    private volatile static Jedis redisClient;

    private static ReentrantLock lock = new ReentrantLock(false);

    public static Jedis getRedisClient(String redisAddress,Integer redisPort){
        Jedis jedis = new Jedis(redisAddress,redisPort);
        return jedis;
    }

    public static Jedis getRedisClient(){
        if(redisClient==null){
            lock.lock();
            if(redisClient!=null){
                redisClient = getRedisClient(redisAddress,redisPort);
            }
            lock.unlock();
        }
        return redisClient;
    }

}
