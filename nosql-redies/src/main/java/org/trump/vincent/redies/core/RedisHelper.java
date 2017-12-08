package org.trump.vincent.redies.core;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vincent on 2017/12/8 0008.
 * @Reference :  https://www.cnblogs.com/liuling/p/2014-4-19-04.html
 */
public class RedisHelper {
    public RedisHelper(Jedis redisClient) {
        this.redisClient = redisClient;
    }

    private Jedis redisClient;


    /**
     * Part1 : CURD operation for the string data type
     */

    /**
     * set value for the key
     * @param key
     * @param value
     */
    public void set(String key,String value){
        this.redisClient.set(key,value);
    }
    public void multiSet(String ...key_values){
        this.redisClient.mset(key_values);
    }

    public void delete(String key){
        this.redisClient.del(key);
    }

    public String get(String key){
        return this.redisClient.get(key);
    }

    public List<String> multiGet(String ...keys){
        return this.redisClient.mget(keys);
    }

    /**
     * the value of the key will increment
     * @param numKey
     */
    public void numValueIncrement(String numKey){
        this.redisClient.incr(numKey);
    }


    /**
     * Part2 : CURD operation for the hash data type
     */

    public void setMap(String key, Map hash){
        this.redisClient.hmset(key,hash);
    }

    public String getHashField(String key,String fieldName){
        return this.redisClient.hget(key,fieldName);
    }

    public List<String> getHashFields(String key,String fields){
        return this.redisClient.hmget(key,fields);
    }

    public void delete(String key,String ...fields){
        this.redisClient.hdel(key,fields);
    }

    public Set<String> loadFieldsName(String key){
        return this.redisClient.hkeys(key);
    }


    /**
     * Part3 : CURD operation for the list data type
     */

    public void setList(String key, List<String> values){
        setList(key,(String[]) values.toArray());
    }

    public void setList(String key,String...values){
        this.redisClient.lpush(key,values);
//        this.redisClient.rpush(key,values);
    }

    /**
     * Part3 : CURD operation for the set data type
     */
}
