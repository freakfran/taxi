package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverCarBindingRelationship;
import com.fran.service.impl.DriverCarBindingRelationshipServiceImpl;
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
@RequestMapping("/driver_car_binding_relationship")
public class DriverCarBindingRelationshipController {
    @Autowired
    private DriverCarBindingRelationshipServiceImpl driverCarBindingRelationshipService;

    @PostMapping("/bind")
    public CommonResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }

    @PostMapping("/unbind")
    public CommonResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }
}
