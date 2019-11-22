package com.qfeng;
import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Set;

/**
 * @ClassName redis
 * @Description: TODO
 * @Author 小米
 * @Date 2019/11/20
 * @Version V1.0
 **/

public class Redis {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.3.128", 6379);
        String s = jedis.get("name");
        System.out.println(s);

        List<String> list = jedis.lrange("user",0, -1);
        System.out.println(list);

        Set<String> user2 = jedis.smembers("user2");
        System.out.println(user2);
    }
}
