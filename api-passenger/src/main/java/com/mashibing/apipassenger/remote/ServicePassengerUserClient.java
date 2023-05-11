package com.mashibing.apipassenger.remote;

import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.VerificationDto;
import com.mashibing.common.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ：53527
 * @title ：ServicePassengerUserClient
 * @date ：Created in 2023/5/10 16:29
 * @description：service-passenger-user feign client
 */
@FeignClient("service-passenger-user")

public interface ServicePassengerUserClient {
    @PostMapping("/user")
    public ResponseResult logOrReg(@RequestBody VerificationDto verificationDto);

    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String phone);
}
