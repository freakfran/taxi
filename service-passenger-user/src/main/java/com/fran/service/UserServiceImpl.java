package com.fran.service;

import com.fran.constant.CommonStatusEnum;
import com.fran.dto.CommonResult;
import com.fran.mapper.PassengerUserMapper;
import com.fran.pojo.PassengerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PassengerUserMapper passengerUserMapper;
    @Override
    public CommonResult loginOrRegister(String passengerPhone) {
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        //判断用户是否存在，如果不存在，创建用户
        if(passengerUsers.size()==0){
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setState((byte)0);
            passengerUser.setGmtCreate(LocalDateTime.now());
            passengerUser.setGmtModified(LocalDateTime.now());
            passengerUserMapper.insert(passengerUser);
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult getUserByPhone(String phone) {
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",phone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if(passengerUsers.size()==0){
            return CommonResult.fail(CommonStatusEnum.USER_NOT_EXIST.getCode(),CommonStatusEnum.USER_NOT_EXIST.getMessage());
        }
        PassengerUser passengerUser = passengerUsers.get(0);

        return CommonResult.success(passengerUser);
    }
}
