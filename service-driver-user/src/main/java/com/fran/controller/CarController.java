package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
}
