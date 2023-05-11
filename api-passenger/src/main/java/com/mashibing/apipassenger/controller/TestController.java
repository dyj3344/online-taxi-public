package com.mashibing.apipassenger.controller;

import com.mashibing.common.dto.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    //需要有token的测试
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth test");
    }
    //不需要有token的测试
    @GetMapping("/noAuthTest")
    public ResponseResult noAuthTest(){
        return ResponseResult.success("no auth test");
    }
}
