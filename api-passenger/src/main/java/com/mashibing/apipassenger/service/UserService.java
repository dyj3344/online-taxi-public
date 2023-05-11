package com.mashibing.apipassenger.service;

import com.mashibing.apipassenger.remote.ServicePassengerUserClient;
import com.mashibing.common.dto.PassengerUser;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResult;
import com.mashibing.common.dto.VerificationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.JwtUtil;

/**
 * @author ：53527
 * @title ：UserService
 * @date ：Created in 2023/5/11 14:58
 * @description：<TODO description class purpose>
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken){
        //解析token,获取到手机号
        log.info("获取到的token:{}",accessToken);
        TokenResult tokenResult = JwtUtil.parseToken(accessToken);
        if(tokenResult!=null) {
            log.info("获取到的手机号为:{}",tokenResult.getPhone());
        }
        //根据手机号查用户信息
        ResponseResult user = servicePassengerUserClient.getUser(tokenResult.getPhone());
        return ResponseResult.success(user.getData());

    }

}
