package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.remote.ServiceVerificationCodeClient;
import com.mashibing.apipassenger.request.VerificationDto;
import com.mashibing.apipassenger.service.VerificationCodeSerivce;
import com.mashibing.common.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {
    @Autowired
    VerificationCodeSerivce verificationCodeSerivce;


    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationDto verificationDto){

        String passengerPhone = verificationDto.getPassengerPhone();
        return verificationCodeSerivce.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult verificationCodeCheck(@RequestBody VerificationDto verificationDto){
        Assert.notNull(verificationDto.getPassengerPhone(),"客户手机号不能为空");
        Assert.notNull(verificationDto.getVerificationCode(),"验证码不能为空");
        return verificationCodeSerivce.checkCode(verificationDto);
    }
}
