package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.service.UserService;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：53527
 * @title ：UserController
 * @date ：Created in 2023/5/11 14:56
 * @description：<TODO description class purpose>
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest httpServletRequest){
            //根据token获取用户信息
        String authorization = httpServletRequest.getHeader("Authorization");
        return userService.getUserByAccessToken(authorization);
    }
}
