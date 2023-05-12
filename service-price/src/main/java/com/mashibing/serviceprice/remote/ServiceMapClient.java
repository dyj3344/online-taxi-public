package com.mashibing.serviceprice.remote;

import com.mashibing.common.dto.ForecastPriceDto;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * service-map的客户端
 * @author ：53527
 * @title ：ServiceMapClient
 * @date ：Created in 2023/5/12 16:48
 * @description：<TODO description class purpose>
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    @GetMapping("/direction/driving")
    public ResponseResult<DirectionResponse> driving(@RequestBody ForecastPriceDto forecastPriceDto);
}
