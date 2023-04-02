package com.fran.service.impl;

import com.fran.dto.CommonResult;
import com.fran.pojo.DriverUserWorkStatus;
import com.fran.mapper.DriverUserWorkStatusMapper;
import com.fran.service.DriverUserWorkStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public CommonResult changeWorkStatus(Long driverId, Integer workStatus) {
        Map<String,Object> map = new HashMap<>();
        map.put("driver_id",driverId);
        List<DriverUserWorkStatus> status = driverUserWorkStatusMapper.selectByMap(map);
        DriverUserWorkStatus driverUserWorkStatus = status.get(0);
        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatus.setGmtCreate(LocalDateTime.now());
        driverUserWorkStatus.setGmtModified(LocalDateTime.now());
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);
        return CommonResult.success();
    }
}
