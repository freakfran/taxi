package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.remote.ServiceDriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Override
    public CommonResult addCar(Car car) {
        return serviceDriverUserClient.addCar(car);
    }
}
