package com.mashibing.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.mashibing.common.constant.TokenConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResult;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;
import utils.RedisPrefixUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

/**
 * @author ：53527
 * @title ：JwtInterceptor
 * @date ：Created in 2023/5/11 9:29
 * @description：<TODO description class purpose>
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result =true;

        String resultString ="";

        String authorization = request.getHeader("Authorization");
        TokenResult tokenResult = JwtUtil.checkToken(authorization);
        //从redis从取出token
        if(tokenResult!=null) {
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();

            String tokenKey = RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstants.ACCESS_TOKEN_TYPE);
            //拦截器是在实例化之前初始化的,所以说这里会出现空指针,需要提前初始化
            String redisToken= stringRedisTemplate.opsForValue().get(tokenKey);
            if(StringUtils.isBlank(redisToken)|| !redisToken.trim().equals(authorization)){
                resultString="access token invalid";
                result =false;
            }
        }else{
            resultString="access token invalid";
            result =false;
        }
        if(!result){
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.failed(resultString)).toString());
        }

        return result;
    }
}
