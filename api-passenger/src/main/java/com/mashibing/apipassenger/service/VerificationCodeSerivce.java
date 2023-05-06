package com.mashibing.apipassenger.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeSerivce {

    public String generatorCode( String passengerPhone){
        System.out.println("调用验证码服务,获取验证码");
        String code="111111";
        System.out.println("存入redis");
        JSONObject result =new JSONObject();
        result.put("code",1);
        result.put("messge","success");
        return result.toString();
    }
}
