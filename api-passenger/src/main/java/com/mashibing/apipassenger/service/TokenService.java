package com.mashibing.apipassenger.service;

import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.constant.TokenConstants;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResponse;
import com.mashibing.common.dto.TokenResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import utils.JwtUtil;
import utils.RedisPrefixUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author ：53527
 * @title ：TokenService
 * @date ：Created in 2023/5/11 13:53
 * @description：<TODO description class purpose>
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     *
     * 刷新token方法
     * @param String refreshToken
     * @return {@link ResponseResult}
     * @throws
     * @author 53527
     * @date 2023/5/11 13:53
     */
    public ResponseResult refreshToken(String refreshTokenSrc){
        //解析refreshToken
        TokenResult tokenResult = JwtUtil.checkToken(refreshTokenSrc);
        if (tokenResult == null) {
             return ResponseResult.failed(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();
        //读取redis中的refreshtoken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        //校验refreshtoken
        if(StringUtils.isBlank(refreshTokenRedis)|| !refreshTokenRedis.trim().equals(refreshTokenSrc)){
            return ResponseResult.failed(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        //生成双token
        String refreshToken = JwtUtil.generatorToken(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtil.generatorToken(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31, TimeUnit.DAYS);

        TokenResponse tokenResponse =new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);


    }
}
