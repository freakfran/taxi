package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUserWorkStatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fran
 * @since 2023-04-02
 */
public interface DriverUserWorkStatusService {
    public CommonResult changeWorkStatus(Long driverId,Integer workStatus);
}
