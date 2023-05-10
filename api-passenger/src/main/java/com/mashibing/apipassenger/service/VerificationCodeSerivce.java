package com.mashibing.apipassenger.service;

import com.mashibing.apipassenger.remote.ServiceVerificationCodeClient;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeSerivce {
    @Autowired
    ServiceVerificationCodeClient serviceVerificationCodeClient;

    public String generatorCode( String passengerPhone){
        System.out.println("调用验证码服务,获取验证码");
        ResponseResult<NumberCodeResponse> numberCode = serviceVerificationCodeClient.getNumberCode(6);
        int code=numberCode.getData().getNumberCode();
        System.out.println("remote code"+code);
        System.out.println("存入redis");
        JSONObject result =new JSONObject();
        result.put("code",1);
        result.put("messge","success");
        return result.toString();
    }
}
