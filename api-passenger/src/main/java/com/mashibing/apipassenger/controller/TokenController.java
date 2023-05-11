package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.service.TokenService;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：53527
 * @title ：TokenController
 * @date ：Created in 2023/5/11 13:51
 * @description：<TODO description class purpose>
 */
@RestController
public class TokenController {

    @Autowired
    TokenService tokenService;
    /**
     *
     * 刷新token的接口
     * @param TokenResponse tokenResponse 刷新的token
     * @return {@link ResponseResult}
     * @throws
     * @author 53527
     * @date 2023/5/11 13:52
     */
    @PostMapping("/token-refresh")
    public ResponseResult refreshToen(@RequestBody TokenResponse tokenResponse){
        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("refreshToken:"+refreshToken);

        return tokenService.refreshToken(refreshToken);
    }
}
