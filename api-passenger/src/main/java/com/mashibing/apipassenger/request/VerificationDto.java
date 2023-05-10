package com.mashibing.apipassenger.request;


import lombok.Data;

@Data
public class VerificationDto {

    private String passengerPhone;

    private String verificationCode;



}
