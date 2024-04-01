package org.example.abc.pack_redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class JedisDemo {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.select(1);
        //String, List, Set, Hash, ZSet
        //testString(jedis);

        //testSet(jedis);

        //testHash(jedis);

        //testZSet(jedis);

        jedis.close();
    }

    private static void testZSet(Jedis jedis) {
        jedis.zadd("zset", 100, "生田绘梨花");
        jedis.zadd("zset", 90, "白石麻衣");
        jedis.zadd("zset", 80, "斋藤飞鸟");
//        Set<String> zset = jedis.zrange("zset", 0, -1);
//        zset.forEach(System.out::println);
        Set<Tuple> tuples = jedis.zrangeWithScores("zset", 0, -1);
        for (Tuple tuple : tuples) {
            System.out.println(tuple.getElement() + "  " + tuple.getScore());
        }
    }

    private static void testHash(Jedis jedis) {
        HashMap<String, String> map = new HashMap<>();
        map.put("乃木坂", "希望");
        map.put("日向坂", "Joyful Love");
        jedis.hset("hash", map);
        List<String> hash = jedis.hvals("hash");
        hash.forEach(System.out::println);
    }

    private static void testSet(Jedis jedis) {
        jedis.sadd("set", "a", "b", "a", "c", "a", "d");
        System.out.println(jedis.scard("set"));
        Set<String> set = jedis.smembers("set");
        set.forEach(System.out::println);
    }

    private static void testString(Jedis jedis) throws InterruptedException {
        jedis.set("name", "张三");
        jedis.set("age", "19");
        Long setnx = jedis.setnx("name", "李四");
        System.out.println(setnx);
        jedis.expire("name", 100);
        TimeUnit.SECONDS.sleep(5);
        Long ttl = jedis.ttl("name");
        System.out.println(ttl);
    }
}
