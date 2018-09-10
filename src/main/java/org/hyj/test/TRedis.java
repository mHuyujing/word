package org.hyj.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TRedis {
    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxWaitMillis(20000);
        JedisPool localhost = new JedisPool(jedisPoolConfig, "localhost");
        Jedis jedis  = localhost.getResource();
        int i = 0;
        long start = System.currentTimeMillis();
       while (true){
           long end = System.currentTimeMillis();
           if (end-start>=3000){
               break;
           }
           i++;
           jedis.set("test"+i,i+"");
       }
        System.out.println(i);
        String test1 = jedis.get("test1");
        System.out.println(test1);
    }
}
