package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverCarBindingRelationship;
import com.fran.remote.ServiceDriverUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverCarBindingRelationshipServiceImpl implements DriverCarBindingRelationshipService{
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Override
    public CommonResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    @Override
    public CommonResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }
}
