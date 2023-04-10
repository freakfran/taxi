package com.fran.service;

import com.fran.constant.OrderConstants;
import com.fran.dto.CommonResult;
import com.fran.mapper.OrderInfoMapper;
import com.fran.pojo.OrderInfo;
import com.fran.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Override
    public CommonResult add(OrderRequest orderRequest) {
        OrderInfo orderInfo = new OrderInfo();
        //从一个实体类把同名的属性copy到另一个
        BeanUtils.copyProperties(orderRequest,orderInfo);

        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        orderInfo.setGmtCreate(LocalDateTime.now());
        orderInfo.setGmtModified(LocalDateTime.now());

        orderInfoMapper.insert(orderInfo);
        log.info(orderInfo.toString());
        return CommonResult.success();
    }
}
