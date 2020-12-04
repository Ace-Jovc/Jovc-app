package redis.test;

import redis.clients.jedis.Jedis;

/**
 * Java使用Redis:连接本地Redis
 *
 * @author zouhui
 * @version 1.0 2019-12-25
 */
public class TestRedis1 {
    public static void main(String[] args) {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        System.out.println("连接成功");
        // 查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }
}

