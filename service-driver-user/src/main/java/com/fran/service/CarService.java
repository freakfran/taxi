package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fran
 * @since 2023-04-01
 */
public interface CarService{
    public CommonResult addCar(Car car);
    public CommonResult<Car> getCarById(Long carId);
}
