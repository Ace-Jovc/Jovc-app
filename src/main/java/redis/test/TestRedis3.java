package redis.test;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * Java使用Redis:向Redis保存List集合
 *
 * @author zouhui
 * @version 1.0 2019-12-25
 */
public class TestRedis3 {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123456");
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        jedis.lpush("site-list", "IE");
        jedis.lpush("site-list", "DR");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
    }
}
