package com.fran.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fran.constant.CommonStatusEnum;
import com.fran.constant.OrderConstants;
import com.fran.dto.CommonResult;
import com.fran.mapper.OrderInfoMapper;
import com.fran.pojo.OrderInfo;
import com.fran.remote.ServicePriceClient;
import com.fran.request.OrderRequest;
import com.fran.util.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ServicePriceClient servicePriceClient;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public CommonResult add(OrderRequest orderRequest) {
        //判断当前订单计价规则是否为最新
        CommonResult<Boolean> isLastest = servicePriceClient.isLatest(orderRequest.getFareType(), orderRequest.getFareVersion());
        if(!isLastest.getData()){
            return CommonResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(),CommonStatusEnum.PRICE_RULE_CHANGED.getMessage());
        }


        //有正在进行的订单，不允许下单
        Long count = countOrderGoingOn(orderRequest.getPassengerId());
        if(count > 0){
            return CommonResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(),CommonStatusEnum.ORDER_GOING_ON.getMessage());
        }


        //下单的设备是黑名单设备，不允许下单
        String deviceCode = orderRequest.getDeviceCode();
        String key = RedisKeyUtils.generateblackDeviceCodeKey(deviceCode);
        //错误，时间和设置值不能分开写
//        Long increment = redisTemplate.opsForValue().increment(key);
//        redisTemplate.expire(key,1, TimeUnit.HOURS);
        Boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            String code = redisTemplate.opsForValue().get(key);
            int i = Integer.parseInt(code);
            if(i >= 2){
                //设备超过下单次数
                return CommonResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(),CommonStatusEnum.DEVICE_IS_BLACK.getMessage());
            }else {
                redisTemplate.opsForValue().increment(key);
            }
        }else {
            redisTemplate.opsForValue().setIfAbsent(key,"1",1,TimeUnit.HOURS);
        }



        //创建订单
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

    private Long countOrderGoingOn(Long passengerId){
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passenger_id",passengerId);
        queryWrapper.and(wrapper -> wrapper.eq("order_status",OrderConstants.ORDER_START)
                .or().eq("order_status",OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status",OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status",OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status",OrderConstants.PICK_UP_PASSENGER)
                .or().eq("order_status",OrderConstants.PASSENGER_GET_OFF)
                .or().eq("order_status",OrderConstants.TO_START_PAY)
        );
        return orderInfoMapper.selectCount(queryWrapper);
    }
}
