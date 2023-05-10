package com.mashibing.apipassenger.remote;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {

    @GetMapping("/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size")int size);

}
