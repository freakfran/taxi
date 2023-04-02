package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverCarBindingRelationship;
import org.springframework.web.bind.annotation.RequestBody;

public interface DriverCarBindingRelationshipService {
    public CommonResult bind(DriverCarBindingRelationship driverCarBindingRelationship);
    public CommonResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
