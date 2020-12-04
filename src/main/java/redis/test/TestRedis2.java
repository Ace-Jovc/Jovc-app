package redis.test;

import redis.clients.jedis.Jedis;

import redis.clients.jedis.Jedis;

/**
 * Java使用Redis:向Redis保存key/value字符串值
 *
 * @author zouhui
 * @version 1.0 2019-12-25
 */
public class TestRedis2 {

    public static void main(String[] args) {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123456");
        System.out.println("连接成功");
        // 设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
        System.out.println(jedis.get("k0"));
    }
}

