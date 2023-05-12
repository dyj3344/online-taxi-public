package com.mashibing.apipassenger.service;

import com.mashibing.common.dto.ForecastPriceDto;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：53527
 * @title ：ForecastPriceSerivce
 * @date ：Created in 2023/5/12 13:57
 * @description：<TODO description class purpose>
 */
@Service
@Slf4j
public class ForecastPriceSerivce {

    public ResponseResult forecastPrice( ForecastPriceDto forecastPriceDto){
        String depLongitude = forecastPriceDto.getDepLongitude();
        String destLongitude = forecastPriceDto.getDestLongitude();
        String depLatitude = forecastPriceDto.getDepLatitude();
        String destLatitude = forecastPriceDto.getDestLatitude();
        log.info("出发经度:{}",depLongitude);
        log.info("出发纬度:{}",depLatitude);
        log.info("目的经度:{}",destLongitude);
        log.info("目的纬度:{}",destLatitude);
        log.info("调用计价服务,计算价格");
        ForecastPriceResponse forecastPriceResponse=new ForecastPriceResponse();
        return ResponseResult.success(forecastPriceResponse);
    }
}
