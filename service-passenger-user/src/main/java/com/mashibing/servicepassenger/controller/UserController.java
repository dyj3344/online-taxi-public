package com.mashibing.servicepassenger.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.VerificationDto;
import com.mashibing.servicepassenger.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserSerivce userSerivce;

    @PostMapping("/user")
    /**
     *
     *
     * @param VerificationDto verificationDto
     * @return {@link ResponseResult}
     * @throws
     * @author 53527
     * @date 2023/5/10 13:49
     */       
    public ResponseResult logOrReg(@RequestBody VerificationDto verificationDto){
        Assert.notNull(verificationDto.getPassengerPhone(),"手机号码不能为空");
        String passengerPhone = verificationDto.getPassengerPhone();
        System.out.println("当前登陆手机号:"+passengerPhone);

        return userSerivce.logOrRegsiter(passengerPhone);
    }
}
