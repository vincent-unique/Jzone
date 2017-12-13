package org.trump.vincent.memcached;

import net.spy.memcached.MemcachedClient;

/**
 * Created by Vincent on 2017/12/8 0008.
 */
public class MemcachedConnection {
    private volatile static MemcachedClient memcachedClient;
    public static String MEMCACHED_HOST = "127.0.0.1";
    public static Integer MEMCACHED_PORT = 11211;


}
