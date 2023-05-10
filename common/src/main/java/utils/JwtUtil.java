package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：53527
 * @title ：JwtUtil
 * @date ：Created in 2023/5/10 17:16
 * @description：<TODO description class purpose>
 */
public class JwtUtil {


    private final static  String SIGN="!uhjS&2j1";

     /**
      *
      * 生成token
      * @param String map
      * @return {@link String}
      * @throws
      * @author 53527
      * @date 2023/5/10 17:18
      */
    public static String  generatorToken(Map<String,String> map){
        //定义过期时间
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date time = calendar.getTime();
        JWTCreator.Builder builder= JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //整合过期时间
        builder.withExpiresAt(time);
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    public static void main(String[] args) {
        Map<String,String> map =new HashMap<>();
        map.put("name","张三");
        map.put("age","12");
        System.out.println( "生成的token,"+generatorToken(map));
    }
    //解析token
}
