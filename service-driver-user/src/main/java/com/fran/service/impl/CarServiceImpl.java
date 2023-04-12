package com.fran.service.impl;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.mapper.CarMapper;
import com.fran.remote.ServiceMapClient;
import com.fran.response.TerminalResponse;
import com.fran.response.TrackResponse;
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
        carMapper.insert(car);
        
        CommonResult<TerminalResponse> result = serviceMapClient.addTerminal(car.getVehicleNo(),car.getId()+"");
        Long tid = result.getData().getTid();

        CommonResult<TrackResponse> trackResponse = serviceMapClient.addTrack(tid);
        Integer trid = trackResponse.getData().getTrid();
        String trname = trackResponse.getData().getTrname();

        car.setTrid(trid);
        car.setTrname(trname);
        car.setTid(tid);
        log.info(car.toString());
        carMapper.updateById(car);
        return CommonResult.success();
    }

    @Override
    public CommonResult<Car> getCarById(Long carId) {
        Car car = carMapper.selectById(carId);
        return CommonResult.success(car);
    }
}
