package com.mashibing.serviceverificationcode.controller;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public String numberCode(@PathVariable("size")int size){
        System.out.println(size);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code","1");
        jsonObject.put("message","success");
        JSONObject data=new JSONObject();
        data.put("numberCode",123456);
        jsonObject.put("data",data);

        return jsonObject.toString();
    }
}
