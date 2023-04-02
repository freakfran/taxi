package com.fran.service.impl;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.mapper.CarMapper;
import com.fran.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fran
 * @since 2023-04-01
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public CommonResult addCar(Car car) {
        car.setGmtCreate(LocalDateTime.now());
        car.setGmtModified(LocalDateTime.now());
        carMapper.insert(car);
        return CommonResult.success();
    }
}
