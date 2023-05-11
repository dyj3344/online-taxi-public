package utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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

    private final static  String JWT_TOKEN_TYPE="tokenType";

    private final static  String JWT_TOKEN_TIME="tokenType";

     /**
      *
      * 生成token
      * @param String map
      * @return {@link String}
      * @throws
      * @author 53527
      * @date 2023/5/10 17:18
      */
    public static String  generatorToken(String passengerPhone,String identity,String tokenType){
        Map<String,String> map =new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENITY,identity);
        map.put(JWT_TOKEN_TYPE,tokenType);

        //防止生成token一样
        map.put(JWT_TOKEN_TIME,Calendar.getInstance().getTime().toString());
        JWTCreator.Builder builder= JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }

    public static void main(String[] args) {

    }
    //解析token

    public static TokenResult  parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENITY).asString();
        TokenResult tokenResult= new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);

        return tokenResult;
    }

    /**
     *
     * 校验token是否解析成功
     * @param String token
     * @return {@link TokenResult}
     * @throws
     * @author 53527
     * @date 2023/5/11 13:59
     */
    public static TokenResult checkToken(String token){
        
        TokenResult tokenResult= null;
        try {
            tokenResult = JwtUtil.parseToken(token);
        }catch (Exception e){
            return null;
        }
        return tokenResult;
    }
}
