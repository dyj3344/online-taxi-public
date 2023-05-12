package com.mashibing.common.dto;

import lombok.Data;

/**
 * 预估服务dto
 * @author ：53527
 * @title ：ForecastPriceDto
 * @date ：Created in 2023/5/12 13:53
 * @description：<TODO description class purpose>
 */

@Data
public class ForecastPriceDto {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String  destLatitude;
}
