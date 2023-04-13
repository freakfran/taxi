package com.fran.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fran.constant.CommonStatusEnum;
import com.fran.constant.DriverCarConstants;
import com.fran.dto.CommonResult;
import com.fran.mapper.DriverCarBindingRelationshipMapper;
import com.fran.mapper.DriverUserMapper;
import com.fran.mapper.DriverUserWorkStatusMapper;
import com.fran.pojo.DriverCarBindingRelationship;
import com.fran.pojo.DriverUser;
import com.fran.pojo.DriverUserWorkStatus;
import com.fran.response.DriverUserExistsResponse;
import com.fran.response.OrderDriverResponse;
import com.fran.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverUserServiceImpl implements DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public CommonResult addDriver(DriverUser driverUser){
        driverUser.setGmtCreate(LocalDateTime.now());
        driverUser.setGmtModified(LocalDateTime.now());
        driverUserMapper.insert(driverUser);

        //初始化司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstants.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(LocalDateTime.now());
        driverUserWorkStatus.setGmtModified(LocalDateTime.now());
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);
        return CommonResult.success();
    }

    @Override
    public CommonResult updateDriver(DriverUser driverUser) {
        driverUser.setGmtModified(LocalDateTime.now());
        int update = driverUserMapper.updateById(driverUser);
        return CommonResult.success();
    }

    @Override
    public CommonResult<DriverUser> getDriverByPhone(String driverPhone) {
        Map<String,Object> map = new HashMap<>();
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if(driverUsers.isEmpty()){
            return CommonResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(),CommonStatusEnum.DRIVER_NOT_EXISTS.getMessage());
        }
        return CommonResult.success(driverUsers.get(0));
    }

    @Override
    public CommonResult<OrderDriverResponse> getAvailableDriver(Long carId) {
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id",carId);
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);
        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(queryWrapper);
        if(driverCarBindingRelationship==null){
            return CommonResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(),CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getMessage());
        }
        Long driverId = driverCarBindingRelationship.getDriverId();

        QueryWrapper<DriverUserWorkStatus> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("driver_id",driverId);
        queryWrapper1.eq("work_status",DriverCarConstants.DRIVER_WORK_STATUS_START);
        DriverUserWorkStatus driverUserWorkStatust = driverUserWorkStatusMapper.selectOne(queryWrapper1);
        if(driverUserWorkStatust==null){
            return CommonResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(),CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getMessage());
        }
        OrderDriverResponse orderDriverResponse = new OrderDriverResponse();
        orderDriverResponse.setDriverId(driverId);
        orderDriverResponse.setCarId(carId);
        Map<String,Object> map = new HashMap<>();
        map.put("id",driverId);
        orderDriverResponse.setDriverPhone(driverUserMapper.selectByMap(map).get(0).getDriverPhone());
        return CommonResult.success(orderDriverResponse);
    }


}
