package com.fran.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fran.constant.CommonStatusEnum;
import com.fran.constant.IdentityConstants;
import com.fran.constant.OrderConstants;
import com.fran.dto.CommonResult;
import com.fran.mapper.OrderInfoMapper;
import com.fran.pojo.Car;
import com.fran.pojo.OrderInfo;
import com.fran.pojo.PriceRule;
import com.fran.remote.ServiceDriverUserClient;
import com.fran.remote.ServiceMapClient;
import com.fran.remote.ServicePriceClient;
import com.fran.remote.ServiceSsePushClient;
import com.fran.request.OrderRequest;
import com.fran.response.OrderDriverResponse;
import com.fran.response.TerminalResponse;
import com.fran.util.RedisKeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServiceMapClient serviceMapClient;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private ServiceSsePushClient serviceSsePushClient;
    @Override
    public CommonResult add(OrderRequest orderRequest) {
        String fareType = orderRequest.getFareType();
        String[] split = fareType.split("\\$");
        //判断当前订单计价规则是否为最新
        CommonResult<Boolean> isLastest = servicePriceClient.isLatest(orderRequest.getFareType(), orderRequest.getFareVersion());
        if(!isLastest.getData()){
            return CommonResult.fail(CommonStatusEnum.PRICE_RULE_CHANGED.getCode(),CommonStatusEnum.PRICE_RULE_CHANGED.getMessage());
        }


        //有正在进行的订单，不允许下单
        Long count = countPassengerOrderGoingOn(orderRequest.getPassengerId());
        if(count > 0){
            return CommonResult.fail(CommonStatusEnum.ORDER_GOING_ON.getCode(),CommonStatusEnum.ORDER_GOING_ON.getMessage());
        }


        //下单的设备是黑名单设备，不允许下单
        String deviceCode = orderRequest.getDeviceCode();
        boolean isBlack = isBlack(deviceCode);
        if(isBlack){
            return CommonResult.fail(CommonStatusEnum.DEVICE_IS_BLACK.getCode(),CommonStatusEnum.DEVICE_IS_BLACK.getMessage());
        }

        //判断该城市该车型是否存在
        boolean isExists = isPriceRuleExists(split[0], split[1]);
        if(!isExists){
            return CommonResult.fail(CommonStatusEnum.CITY_SERVICE_NOT_EXISTS.getCode(),CommonStatusEnum.CITY_SERVICE_NOT_EXISTS.getMessage());
        }

        //判断当前城市是否有司机
        CommonResult<Boolean> hasAvailableDriver = serviceDriverUserClient.hasAvailableDriver(split[0]);
        if(!hasAvailableDriver.getData()){
            return CommonResult.fail(CommonStatusEnum.CITY_DRIVER_EMPTY.getCode(),CommonStatusEnum.CITY_DRIVER_EMPTY.getMessage());
        }

        //创建订单
        OrderInfo orderInfo = new OrderInfo();
        //从一个实体类把同名的属性copy到另一个
        BeanUtils.copyProperties(orderRequest,orderInfo);
        log.info(orderRequest.toString());
        log.info(orderInfo.toString());
        orderInfo.setOrderStatus(OrderConstants.ORDER_START);
        orderInfo.setGmtCreate(LocalDateTime.now());
        orderInfo.setGmtModified(LocalDateTime.now());

        orderInfoMapper.insert(orderInfo);
        //派单

        log.info(orderInfo.toString());
        for(int i = 0;i < 6;i++){
            CommonResult<List<TerminalResponse>> listCommonResult = dispatchRealTimeOrder(orderInfo.getId());
            if(listCommonResult.getCode()==CommonStatusEnum.SUCCESS.getCode()){
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult toPickUpPassenger(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();
        LocalDateTime toPickUpPassengerTime = orderRequest.getToPickUpPassengerTime();
        String toPickUpPassengerAddress = orderRequest.getToPickUpPassengerAddress();
        String toPickUpPassengerLongitude = orderRequest.getToPickUpPassengerLongitude();
        String toPickUpPassengerLatitude = orderRequest.getToPickUpPassengerLatitude();
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfo.setToPickUpPassengerAddress(toPickUpPassengerAddress);
        orderInfo.setToPickUpPassengerTime(toPickUpPassengerTime);
        orderInfo.setToPickUpPassengerLongitude(toPickUpPassengerLongitude);
        orderInfo.setToPickUpPassengerLatitude(toPickUpPassengerLatitude);
        orderInfo.setOrderStatus(OrderConstants.DRIVER_TO_PICK_UP_PASSENGER);
        orderInfoMapper.updateById(orderInfo);
        return CommonResult.success();
    }

    @Override
    public CommonResult arriveDeparture(OrderRequest orderRequest) {
        Long orderId = orderRequest.getOrderId();
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfo.setOrderStatus(OrderConstants.DRIVER_ARRIVED_DEPARTURE);
        orderInfo.setDriverArrivedDepartureTime(LocalDateTime.now());
        orderInfoMapper.updateById(orderInfo);
        return CommonResult.success();
    }

    private Long countPassengerOrderGoingOn(Long passengerId){
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

    private Long countDriverOrderGoingOn(Long driverId){
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverId);
        queryWrapper.and(wrapper -> wrapper.eq("order_status",OrderConstants.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status",OrderConstants.DRIVER_TO_PICK_UP_PASSENGER)
                .or().eq("order_status",OrderConstants.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status",OrderConstants.PICK_UP_PASSENGER)
        );
        return orderInfoMapper.selectCount(queryWrapper);
    }

    private boolean isBlack(String deviceCode){
        String key = RedisKeyUtils.generateblackDeviceCodeKey(deviceCode);
        //错误，时间和设置值不能分开写
        //Long increment = redisTemplate.opsForValue().increment(key);
        //redisTemplate.expire(key,1, TimeUnit.HOURS);
        Boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            String code = redisTemplate.opsForValue().get(key);
            int i = Integer.parseInt(code);
            if(i >= 100){
                //设备超过下单次数
                return true;
            }else {
                redisTemplate.opsForValue().increment(key);
            }
        }else {
            redisTemplate.opsForValue().setIfAbsent(key,"1",1,TimeUnit.HOURS);
        }
        return false;
    }

    private boolean isPriceRuleExists(String cityCode,String vehicleType){
        PriceRule priceRule = new PriceRule();
        priceRule.setVehicleType(vehicleType);
        priceRule.setCityCode(cityCode);
        return servicePriceClient.isExists(priceRule).getData();
    }

    public CommonResult<List<TerminalResponse>> dispatchRealTimeOrder(Long orderId){
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        String depLongitude = orderInfo.getDepLongitude();
        String depLatitude = orderInfo.getDepLatitude();
        String center = depLatitude + "," + depLongitude;
        CommonResult<List<TerminalResponse>> listCommonResult = new CommonResult<>();
        List<Integer> radiusList = new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);
        for(int i = 0;i < radiusList.size();i++){
            int radius = radiusList.get(i);
            log.info("第"+(i+1)+"次找车，半径"+radius+"km");
            //寻找终端
            listCommonResult = serviceMapClient.aroundSearch(center, radius);
            List<TerminalResponse> data = listCommonResult.getData();
            if(!data.isEmpty()){
                //找到附近的车辆，解析它们的id
                //log.info(listCommonResult.getData().toString());
                //解析结果[TerminalResponse(tid=667708883, carId=1646346144961314817)]
                JSONArray carArray = JSONArray.parseArray(JSON.toJSONString(listCommonResult.getData()));
                for(int j = 0;j < carArray.size();j++){
                    JSONObject car = carArray.getJSONObject(j);
                    String longitude = car.getString("longitude");
                    String latitude = car.getString("latitude");
                    String s = car.getString("carId");
                    long carId = Long.parseLong(s);
                    //确认该车是否有出车的司机
                    CommonResult<OrderDriverResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                    if(availableDriver.getCode()!=CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode()){
                        //log.info("找到司机:" + availableDriver.getData().getDriverId() + "，车辆ID：" + carId);
                        //确认该司机是否有进行中的订单
                        OrderDriverResponse driverData = availableDriver.getData();
                        Long driverId = driverData.getDriverId();
                        //解决并发问题，一定要用internal
                        //synchronized ((driverId+"").intern())
                        String lockKey = (driverId+"").intern();
                        RLock lock = redissonClient.getLock(lockKey);
                        lock.lock();
                        Long count = countDriverOrderGoingOn(driverId);
                        if(count == 0){
                            //把订单派给司机
                            orderInfo.setCarId(carId);
                            orderInfo.setDriverId(driverId);
                            orderInfo.setDriverPhone(driverData.getDriverPhone());
                            orderInfo.setReceiveOrderTime(LocalDateTime.now());
                            orderInfo.setReceiveOrderCarLongitude(longitude);
                            orderInfo.setReceiveOrderCarLatitude(latitude);
                            orderInfo.setLicenseId(driverData.getLicenseId());
                            orderInfo.setVehicleNo(driverData.getVehicleNo());
                            orderInfo.setOrderStatus(OrderConstants.DRIVER_RECEIVE_ORDER);
                            orderInfoMapper.updateById(orderInfo);
                            lock.unlock();
                            //通知司机
                            JSONObject driverContent = new JSONObject();
                            driverContent.put("passengerId", orderInfo.getPassengerId());
                            driverContent.put("passengerPhone", orderInfo.getPassengerPhone());
                            driverContent.put("departure", orderInfo.getDeparture());
                            driverContent.put("depLongitude", orderInfo.getDepLongitude());
                            driverContent.put("depLatitude", orderInfo.getDepLatitude());
                            driverContent.put("destination", orderInfo.getDestination());
                            driverContent.put("destLongitude", orderInfo.getDestLongitude());
                            driverContent.put("destLatitude", orderInfo.getDestLatitude());
                            serviceSsePushClient.push(orderInfo.getDriverId().toString(), IdentityConstants.IDENTITY_DRIVER,driverContent.toJSONString());
                            //通知乘客
                            JSONObject passengerContent = new JSONObject();
                            passengerContent.put("driverId", orderInfo.getDriverId());
                            passengerContent.put("driverPhone", orderInfo.getDriverPhone());
                            passengerContent.put("vehicleNo", orderInfo.getVehicleNo());
                            //车辆信息
                            CommonResult<Car> carById = serviceDriverUserClient.getCarById(orderInfo.getCarId());
                            Car taxi = carById.getData();
                            passengerContent.put("brand", taxi.getBrand());
                            passengerContent.put("model", taxi.getModel());
                            passengerContent.put("vehicleColor", taxi.getVehicleColor());



                            passengerContent.put("receiveOrderCarLongitude", orderInfo.getReceiveOrderCarLongitude());
                            passengerContent.put("receiveOrderCarLatitude", orderInfo.getReceiveOrderCarLatitude());
                            serviceSsePushClient.push(orderInfo.getPassengerId().toString(), IdentityConstants.IDENTITY_PASSENGER,passengerContent.toJSONString());
                            return CommonResult.success(carArray.get(i));
                        }
                        lock.unlock();


                    }
                }
            }
            radius+=2000;
        }
        return CommonResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getCode(),CommonStatusEnum.AVAILABLE_DRIVER_EMPTY.getMessage());
    }
}
