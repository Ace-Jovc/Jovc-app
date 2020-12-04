package redis.test;

import java.util.*;

import redis.clients.jedis.Jedis;

/**
 * Java使用Redis:获取keys值
 *
 * @author zouhui
 * @version 1.0 2019-12-25
 */
public class TestRedis4 {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        System.out.println("连接成功");

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){// 使用hasNext()检查序列中是否还有元素。
            String key = it.next();//第一次调用Iterator的next()方法时，它返回序列的第一个元素
            System.out.println(key);
        }
    }
}