package com.qfeng;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @ClassName com.qfeng.userController
 * @Description: TODO
 * @Author 小米
 * @Date 2019/11/20
 * @Version V1.0
 **/
@RestController
public class userController {
    @RequestMapping("/sendCode")
    public String sendCode(String phone) {
        if (phone == null) {
            return "error";
        }
        String verfyCode = getCode(6);

        Jedis jedis = new Jedis("192.168.3.128",6379);
        String phonekey = "phone_num:" + phone;
        jedis.setex(phonekey,60, verfyCode);

        String num = jedis.get("num" + phone);
        if (num == null) {
            jedis.setex("num" + phone, 3600 * 24, "3");
        } else if (num != null && !num.equals("1")) {
            jedis.decr("num" + phone);
        } else if (jedis.get("num" + phone).equals("1")) {
            return num;
        }
        System.out.println(verfyCode);
        jedis.close();
        return "success";
    }

    private String getCode(int codelength) {
        String code = "";
        for (int i = 0; i < codelength; i++) {
            int num = new Random().nextInt(10);
            code += num;
        }
        return code;
    }

    @RequestMapping("/verifiCode")
    public String verifiCode(String phone,String verify_code){
        //判断参数
        if(verify_code==null){
            return "error";
        }

        //验证
        Jedis jedis = new Jedis("192.168.3.128",6379);
        String phonekey = jedis.get("phone_num:"+phone);

        System.out.println(phonekey);

        if(verify_code.equals(phonekey)){
            return "success";
        }
        jedis.close();
        return "error";
    }
}
