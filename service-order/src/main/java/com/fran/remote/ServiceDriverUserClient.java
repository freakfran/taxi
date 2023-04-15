package com.fran.remote;

import com.fran.dto.CommonResult;
import com.fran.pojo.Car;
import com.fran.response.OrderDriverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @GetMapping("/city_driver/hasAvailableDriver")
    public CommonResult<Boolean> hasAvailableDriver(@RequestParam("cityCode") String cityCode);

    @GetMapping("/get_available_driver/{carId}")
    public CommonResult<OrderDriverResponse> getAvailableDriver(@PathVariable("carId")Long carId);

    @GetMapping("/car")
    public CommonResult<Car> getCarById(@RequestParam("carId")Long carId);
}
