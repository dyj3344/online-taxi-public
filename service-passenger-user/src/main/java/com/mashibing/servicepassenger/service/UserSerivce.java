package com.mashibing.servicepassenger.service;

import com.mashibing.common.constant.CommonStatusEnum;
import com.mashibing.common.dto.ResponseResult;
import com.mashibing.servicepassenger.dto.PassengerUser;
import com.mashibing.servicepassenger.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSerivce {
    @Autowired
    private PassengerUserMapper passengerUserMapper;
    /**
     *
     * 根据手机号判断是否需要注册用户
     * @param String passengerPhone 用户手机号
     * @return {@link ResponseResult}
     * @throws
     * @author 53527
     * @date 2023/5/10 13:47
     */
    public ResponseResult logOrRegsiter(String passengerPhone){
        Map<String,Object> map =new HashMap<>();
        int insert = 0;
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if(CollectionUtils.isEmpty(passengerUsers)){
            PassengerUser passengerUser=new PassengerUser();
            passengerUser.setPassengerName("测试");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);
            LocalDateTime localDateTime= LocalDateTime.now();
            passengerUser.setGmtCreate(localDateTime);
            passengerUser.setGmtModified(localDateTime);
            insert = passengerUserMapper.insert(passengerUser);
        }

        return ResponseResult.success(insert,"插入成功,成功条数");
    }
}
