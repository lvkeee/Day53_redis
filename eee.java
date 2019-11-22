# Day53_redis
redis
public class eee {
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
