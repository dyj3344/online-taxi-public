package com.mashibing.servicemap.controller;

import com.mashibing.common.dto.ForecastPriceDto;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.common.response.DirectionResponse;
import com.mashibing.servicemap.serivce.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 计算路程
 * @author ：53527
 * @title ：DirectionController
 * @date ：Created in 2023/5/12 14:38
 * @description：<TODO description class purpose>
 */

@RestController
@RequestMapping("/direction")
public class DirectionController {

    @Autowired
    DirectionService directionService;

    @GetMapping("/driving")
    public ResponseResult<DirectionResponse> driving(@RequestBody ForecastPriceDto forecastPriceDto){


        return directionService.direction(forecastPriceDto);
    }
}
