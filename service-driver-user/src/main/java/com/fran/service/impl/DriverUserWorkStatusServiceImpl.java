package com.fran.service.impl;

import com.fran.pojo.DriverUserWorkStatus;
import com.fran.mapper.DriverUserWorkStatusMapper;
import com.fran.service.DriverUserWorkStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fran
 * @since 2023-04-02
 */
@Service
public class DriverUserWorkStatusServiceImpl implements DriverUserWorkStatusService {
    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
}
