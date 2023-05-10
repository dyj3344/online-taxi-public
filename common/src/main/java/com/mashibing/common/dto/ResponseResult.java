package com.mashibing.common.dto;

import com.mashibing.common.constant.CommonStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    private int code;

    private String message;

    private T data;




        public static <T> ResponseResult success(){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }
    public static <T> ResponseResult success(String message){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(message);
    }
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setData(data).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }
    public static <T> ResponseResult success(T data,String message){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setData(data).setMessage(message);
    }
    public static  ResponseResult failed(int code,String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }
    public static   ResponseResult failed(int code,String message,String data){
        return new ResponseResult().setCode(code).setData(data).setMessage(message);
    }
    public static <T> ResponseResult failed(T data){
        return new ResponseResult().setCode(CommonStatusEnum.FAILED.getCode()).setData(data).setMessage(CommonStatusEnum.FAILED.getValue());
    }


}
