package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUserWorkStatus;
import com.fran.service.impl.DriverUserWorkStatusServiceImpl;
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
 * @since 2023-04-02
 */
@RestController
public class DriverUserWorkStatusController {
    @Autowired
    private DriverUserWorkStatusServiceImpl driverUserWorkStatusService;

    @PostMapping("/driver_user_work_status")
    public CommonResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        return driverUserWorkStatusService.changeWorkStatus(driverUserWorkStatus.getDriverId(),driverUserWorkStatus.getWorkStatus());
    }


}
