package com.mashibing.verficationcode.controller;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size")int size){
       int data=(int)((Math.random()*9+1)*Math.pow(10,size-1));
        NumberCodeResponse numberCodeResponse=new NumberCodeResponse();
        numberCodeResponse.setNumberCode(data);
        return ResponseResult.success(numberCodeResponse);
    }
}
