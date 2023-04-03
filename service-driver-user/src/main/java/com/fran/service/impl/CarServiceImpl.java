package com.fran.service.impl;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.mapper.CarMapper;
import com.fran.remote.ServiceMapClient;
import com.fran.response.TerminalResponse;
import com.fran.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private ServiceMapClient serviceMapClient;

    @Override
    public CommonResult addCar(Car car) {
        car.setGmtCreate(LocalDateTime.now());
        car.setGmtModified(LocalDateTime.now());
        CommonResult<TerminalResponse> result = serviceMapClient.add(car.getVehicleNo());
        log.info(result.getMessage());
        Integer tid = result.getData().getTid();
        car.setTid(tid);
        log.info(car.toString());
        carMapper.insert(car);
        return CommonResult.success();
    }
}
