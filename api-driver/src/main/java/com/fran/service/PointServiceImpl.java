package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.remote.ServiceDriverUserClient;
import com.fran.remote.ServiceMapClient;
import com.fran.request.ApiDriverPointRequest;
import com.fran.request.PointRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PointServiceImpl implements PointService{
    @Autowired
    private ServiceMapClient serviceMapClient;
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Override
    public CommonResult upload(ApiDriverPointRequest apiDriverPointRequest) {
        //获取carId
        Long carId = apiDriverPointRequest.getCarId();
        //通过carId获取triId和tid
        CommonResult<Car> carById = serviceDriverUserClient.getCarById(carId);
        Integer tid = Integer.parseInt(String.valueOf(carById.getData().getTid()));
        Integer trid = carById.getData().getTrid();
        //调用地图接口上传
        PointRequest pointRequest = new PointRequest();
        pointRequest.setPoints(apiDriverPointRequest.getPoints());
        pointRequest.setTid(tid);
        pointRequest.setTrid(trid);

        return serviceMapClient.upload(pointRequest);
    }
}
