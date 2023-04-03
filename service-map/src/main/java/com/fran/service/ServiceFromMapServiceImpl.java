package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFromMapServiceImpl implements ServiceFromMapService {
    @Autowired
    private ServiceClient serviceClient;
    @Override
    public CommonResult add(String name) {
        return serviceClient.add(name);
    }
}
