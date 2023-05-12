package com.mashibing.serviceprice.controller;

import com.mashibing.common.dto.ForecastPriceDto;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：53527
 * @title ：ForecastPriceController
 * @date ：Created in 2023/5/12 14:19
 * @description：<TODO description class purpose>
 */
@RestController
public class ForecastPriceController {
    @Autowired
    ForecastPriceService forecastPriceSerivce;

    @PostMapping("/forecast-price")
    public ResponseResult forecastPrice(@RequestBody ForecastPriceDto forecastPriceDto){

        return forecastPriceSerivce.forecastPrice(forecastPriceDto);
    }
}
