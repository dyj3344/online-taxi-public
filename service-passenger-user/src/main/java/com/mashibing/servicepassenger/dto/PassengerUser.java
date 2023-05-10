package com.mashibing.servicepassenger.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：53527
 * @title ：PassengerUser
 * @date ：Created in 2023/5/10 14:36
 * @description：<TODO description class purpose>
 */
@Data
public class PassengerUser {

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;

    private String profilePhoto;
}
