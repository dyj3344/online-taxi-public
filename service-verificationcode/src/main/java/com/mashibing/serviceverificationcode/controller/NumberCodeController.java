package com.mashibing.serviceverificationcode.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size")int size){
        //todo 生成验证码
        double random=(Math.random()*9+1)*Math.pow(10,size-1);
        int result = (int) random;
        NumberCodeResponse response=new NumberCodeResponse();
        response.setNumberCode(result);

        return ResponseResult.success(response);
    }
}
