package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverCarBindingRelationship;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fran
 * @since 2023-04-01
 */
public interface DriverCarBindingRelationshipService {
    public CommonResult bind(DriverCarBindingRelationship driverCarBindingRelationship);
    public CommonResult unbind(DriverCarBindingRelationship driverCarBindingRelationship);
}
