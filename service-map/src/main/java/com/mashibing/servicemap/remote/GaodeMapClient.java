package com.mashibing.servicemap.remote;

import com.mashibing.common.constant.MapConfigConstant;
import com.mashibing.common.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 高德调用地图api
 * @author ：53527
 * @title ：GaodeMapClient
 * @date ：Created in 2023/5/12 15:00
 * @description：<TODO description class purpose>
 */
@Service
@Slf4j
public class GaodeMapClient {

    @Value("${amap.key}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude , String destLatitude){
        //组装url
        log.info("key是:{}",key);
        //https://restapi.amap.com/v3/direction/driving?
        // origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=xml&key=dd449deb366e5c92a4468ea9389ffb85
        StringBuilder url = new StringBuilder(MapConfigConstant.DIRECTION_URL);
        url.append("origin=")
        .append(depLongitude)
        .append(",")
        .append(depLatitude)
        .append("&destination=")
        .append(destLongitude).append(",").append(destLatitude)
        .append("&extensions=all&output=JSON&key=")
        .append(key).append("&strategy=10");

        log.info("url为:{}",url.toString());

        //调用接口
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        String entityBody = forEntity.getBody();
        //解析接口
        DirectionResponse directionResponse = parseDirection(entityBody);

        return directionResponse;
    }

    private DirectionResponse parseDirection(String direction){
        DirectionResponse directionResponse=null;

        try{

            JSONObject result = JSONObject.fromObject(direction);
            if(result.has(MapConfigConstant.STATUS)) {
                if (result.getInt(MapConfigConstant.STATUS) == 1) {
                    if(result.has(MapConfigConstant.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(MapConfigConstant.ROUTE);
                        JSONArray jsonArray = routeObject.getJSONArray(MapConfigConstant.PATHS);
                        JSONObject pathObject = jsonArray.getJSONObject(0);
                        directionResponse= new DirectionResponse();
                        if(pathObject.has(MapConfigConstant.DISTANCE)){
                            int distance = pathObject.getInt(MapConfigConstant.DISTANCE);
                            directionResponse.setDistance(distance);
                        }
                        if(pathObject.has(MapConfigConstant.DURATION)){
                            int duration  = pathObject.getInt(MapConfigConstant.DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return directionResponse;
    }
}
