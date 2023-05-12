package com.mashibing.apipassenger.controller;

import com.mashibing.apipassenger.service.ForecastPriceSerivce;
import com.mashibing.common.dto.ForecastPriceDto;
import com.mashibing.common.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预估价格
 * @author ：53527
 * @title ：ForecastPriceController
 * @date ：Created in 2023/5/12 13:49
 * @description：<TODO description class purpose>
 */
@RestController
@Slf4j
public class ForecastPriceController {

    @Autowired
    ForecastPriceSerivce forecastPriceSerivce;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDto forecastPriceDto){

        return forecastPriceSerivce.forecastPrice(forecastPriceDto);
    }
}
