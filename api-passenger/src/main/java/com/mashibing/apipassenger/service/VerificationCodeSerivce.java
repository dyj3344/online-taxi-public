package com.mashibing.apipassenger.service;

import com.mashibing.apipassenger.remote.ServiceVerificationCodeClient;
import com.mashibing.apipassenger.request.VerificationDto;
import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.dto.TokenResponse;
import com.mashibing.common.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeSerivce {
    @Autowired
    ServiceVerificationCodeClient serviceVerificationCodeClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static  String VERIFICATIONCODEREF="passenger-verification-code-";

    /**
     *生成验证码
     * @param passengerPhone 手机号
     *
     */

    public ResponseResult generatorCode( String passengerPhone){
        ResponseResult<NumberCodeResponse> numberCode = serviceVerificationCodeClient.getNumberCode(6);
        int code=numberCode.getData().getNumberCode();
        String key = VERIFICATIONCODEREF+passengerPhone;
        stringRedisTemplate.opsForValue().set(key,code+"",2, TimeUnit.MINUTES);
        //通过短信服务商,将对应的验证码发到手机上,阿里短信服务,腾讯短信通,华信,容联 todo

        return ResponseResult.success();
    }
    /**
    * 校验验证码
    * @param verificationDto.passengerPhone 手机号
    * @param verificationDto.verificationCode 验证码
    * */
    public ResponseResult checkCode( VerificationDto verificationDto){
        String passengerPhone = verificationDto.getPassengerPhone();
        String verificationCode = verificationDto.getVerificationCode();
        //根据手机号去redis找验证码
        String key = generatorKeyPhone(passengerPhone);
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value:"+codeRedis);
        //校验验证码
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.failed(CommonStatusEnum.VERIFICATION_CODE_EXPIRE.getCode(),CommonStatusEnum.VERIFICATION_CODE_EXPIRE.getValue());
        }else if(!verificationCode.equals(codeRedis)){
            return ResponseResult.failed(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //判断原来是否有用户,并进行处理


        //颁发令牌
        TokenResponse tokenResponse =new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
    /**
     * 根据手机号生成redis的key
     * @param passengerPhone 手机号
     * */
    private String generatorKeyPhone(String passengerPhone){
        return new StringBuilder(VERIFICATIONCODEREF).append(passengerPhone).toString();
    }
}
