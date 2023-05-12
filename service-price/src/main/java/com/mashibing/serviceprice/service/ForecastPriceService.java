package com.mashibing.serviceprice.service;

import com.mashibing.common.dto.ForecastPriceDto;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.DirectionResponse;
import com.mashibing.common.response.ForecastPriceResponse;
import com.mashibing.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author ：53527
 * @title ：ForecastPriceService
 * @date ：Created in 2023/5/12 14:20
 * @description：<TODO description class purpose>
 */
@Service
@Slf4j
public class ForecastPriceService {
    
    @Autowired
    ServiceMapClient serviceMapClient;
    
    public ResponseResult forecastPrice(ForecastPriceDto forecastPriceDto){
        String depLongitude = forecastPriceDto.getDepLongitude();
        String destLongitude = forecastPriceDto.getDestLongitude();
        String depLatitude = forecastPriceDto.getDepLatitude();
        String destLatitude = forecastPriceDto.getDestLatitude();
        log.info("出发经度:{}",depLongitude);
        log.info("出发纬度:{}",depLatitude);
        log.info("目的经度:{}",destLongitude);
        log.info("目的纬度:{}",destLatitude);
        log.info("调用计价服务,计算价格");
        ResponseResult<DirectionResponse> driving = serviceMapClient.driving(forecastPriceDto);
        ForecastPriceResponse forecastPriceResponse=new ForecastPriceResponse();
        if(driving!=null && driving.getData()!=null){
            BigDecimal price = BigDecimal.valueOf(driving.getData().getDistance()).multiply(BigDecimal.valueOf(driving.getData().getDuration()));
            forecastPriceResponse.setPrice(price);
        }
        return ResponseResult.success(forecastPriceResponse);
    }
}
