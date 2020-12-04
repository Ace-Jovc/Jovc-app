package redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis5 {

    public static void main(String[] args) {
        Jedis jedis=getJedis();
        returnResource(jedis);
    }

    // 服务器IP地址
    private static String ADDR = "localhost";
    // 端口
    private static int PORT = 6379;
    // 密码
    private static String AUTH = "123456";
    // 连接实例的最大连接数
    private static int MAX_ACTIVE = 300;
    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    // 连接超时的时间
    private static int TIMEOUT = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    // 数据库模式是16个数据库 0~15
    public static final int DEFAULT_DATABASE = 0;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            //创建连接池对象
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH, DEFAULT_DATABASE);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    /**
     * 获取Jedis实例
     */
    public synchronized static Jedis getJedis() {
        try {

            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();////获取Jedis对象
                System.out.println("redis--服务正在运行: " + resource.ping());
                String k0=resource.get("k0");
                System.out.println("address:"+k0);
                k0=new String(k0.getBytes("utf-8"));
                System.out.println("address:"+k0);

                //按指定编码存储
                resource.set("chinese", new String("我是中国人".getBytes("utf-8")));
                System.out.println(resource.get("chinese"));

                resource.set("c2", "中国共产党");
                System.out.println(resource.get("c2"));

                return resource;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     *
     * 释放资源
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            // jedisPool.returnResource(jedis);
            jedis.close();
            jedisPool.close();
        }
    }
}

