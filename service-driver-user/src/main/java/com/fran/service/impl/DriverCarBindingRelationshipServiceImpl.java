package com.fran.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fran.constant.CommonStatusEnum;
import com.fran.constant.DriverCarConstants;
import com.fran.dto.CommonResult;
import com.fran.pojo.DriverCarBindingRelationship;
import com.fran.mapper.DriverCarBindingRelationshipMapper;
import com.fran.service.DriverCarBindingRelationshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fran
 * @since 2023-04-01
 */
@Service
public class DriverCarBindingRelationshipServiceImpl implements DriverCarBindingRelationshipService {
    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    @Override
    public CommonResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        //判断：已经绑定的不允许再绑定
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);

        Long count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(count > 0){
            return CommonResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_EXISTS.getMessage());
        }

        //司机已绑定
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);

        count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(count > 0){
            return CommonResult.fail(CommonStatusEnum.DRIVER_BIND_EXISTS.getCode(),CommonStatusEnum.DRIVER_BIND_EXISTS.getMessage());
        }

        //车辆已绑定
        queryWrapper = new QueryWrapper();
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("binding_state",DriverCarConstants.DRIVER_CAR_BIND);

        count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if(count > 0){
            return CommonResult.fail(CommonStatusEnum.CAR_BIND_EXISTS.getCode(),CommonStatusEnum.CAR_BIND_EXISTS.getMessage());
        }

        driverCarBindingRelationship.setBindingTime(LocalDateTime.now());
        driverCarBindingRelationship.setBindingState(DriverCarConstants.DRIVER_CAR_BIND);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return CommonResult.success();
    }

    @Override
    public CommonResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        //查看是否已绑定
        Map<String,Object> map = new HashMap<>();
        map.put("driver_id",driverCarBindingRelationship.getDriverId());
        map.put("car_id",driverCarBindingRelationship.getCarId());
        map.put("binding_state",DriverCarConstants.DRIVER_CAR_BIND);
        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        if(driverCarBindingRelationships.isEmpty()){//未绑定
            return CommonResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXISTS.getMessage());
        }
        driverCarBindingRelationship = driverCarBindingRelationships.get(0);
        driverCarBindingRelationship.setUnBindingTime(LocalDateTime.now());
        driverCarBindingRelationship.setBindingState(DriverCarConstants.DRIVER_CAR_UNBIND);
        driverCarBindingRelationshipMapper.updateById(driverCarBindingRelationship);
        return CommonResult.success();
    }
}
