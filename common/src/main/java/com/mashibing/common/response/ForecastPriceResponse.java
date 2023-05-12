package com.mashibing.common.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ：53527
 * @title ：ForecastPriceResponse
 * @date ：Created in 2023/5/12 13:56
 * @description：<TODO description class purpose>
 */
@Data
public class ForecastPriceResponse {
    private BigDecimal price;
}
