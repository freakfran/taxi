package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fran
 * @since 2023-04-01
 */
@RestController
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @PostMapping("/car")
    public CommonResult addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/car")
    public CommonResult<Car> getCarById(Long carId){
        return carService.getCarById(carId);
    }
}
