package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mashibing.common.dto.TokenResult;

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

    private final static  String JWT_KEY_PHONE="Phone";

    //乘客是1,司机是2
    private final static  String JWT_KEY_IDENITY="idenity";

     /**
      *
      * 生成token
      * @param String map
      * @return {@link String}
      * @throws
      * @author 53527
      * @date 2023/5/10 17:18
      */
    public static String  generatorToken(String passengerPhone,String identity){
        Map<String,String> map =new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENITY,identity);
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
        String token = generatorToken("13098782940", "1");
        System.out.println("生成的token:"+token);
        TokenResult tokenResult = parseToken(token);
        System.out.println("手机号是:+"+tokenResult.getPhone()+",身份是:"+tokenResult.getIdentity());

    }
    //解析token

    public static TokenResult  parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity = verify.getClaim(JWT_KEY_IDENITY).toString();
        TokenResult tokenResult= new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);

        return tokenResult;
    }
}
