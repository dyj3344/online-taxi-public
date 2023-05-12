package com.mashibing.servicemap.serivce;

import com.mashibing.common.response.DirectionResponse;
import com.mashibing.common.dto.ForecastPriceDto;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicemap.remote.GaodeMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：53527
 * @title ：DirectionService
 * @date ：Created in 2023/5/12 14:40
 * @description：<TODO description class purpose>
 */
@Service
@Slf4j
public class DirectionService {
    @Autowired
    GaodeMapClient gaodeMapClient;

    /**
     *
     *根据起点经纬度和终点经纬度获取 距离(米)和时长(分钟)
     * @param ForecastPriceDto forecastPriceDto
     * @return {@link ResponseResult}
     * @throws
     * @author 53527
     * @date 2023/5/12 14:43
     */       
    public ResponseResult<DirectionResponse> direction(ForecastPriceDto forecastPriceDto){
        String depLongitude = forecastPriceDto.getDepLongitude();
        String destLongitude = forecastPriceDto.getDestLongitude();
        String depLatitude = forecastPriceDto.getDepLatitude();
        String destLatitude = forecastPriceDto.getDestLatitude();
        log.info("出发经度:{}",depLongitude);
        log.info("出发纬度:{}",depLatitude);
        log.info("目的经度:{}",destLongitude);
        log.info("目的纬度:{}",destLatitude);
        DirectionResponse direction = gaodeMapClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        return ResponseResult.success(direction);
    }
}
