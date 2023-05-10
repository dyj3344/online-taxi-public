package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.remote.ServiceVerificationCodeClient;
import com.mashibing.apipassenger.request.VerificationDto;
import com.mashibing.apipassenger.service.VerificationCodeSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {
    @Autowired
    VerificationCodeSerivce verificationCodeSerivce;


    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationDto verificationDto){

        String passengerPhone = verificationDto.getPassengerPhone();
        System.out.println(passengerPhone);
        return verificationCodeSerivce.generatorCode(passengerPhone);

    }
}
