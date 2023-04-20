package com.fran.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum CommonStatusEnum {
    SUCCESS(1,"success"),
    FAIL(0,"fail"),
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    TOKEN_ERROR(1199,"token错误"),
    USER_NOT_EXIST(1200,"当前用户不存在"),
    PRICE_RULE_EMPTY(1300,"计价规则不存在"),
    PRICE_RULE_EXISTS(1301,"计价规则已存在"),
    PRICE_RULE_CHANGED(1302,"计价规则有变化"),
    MAP_DISTRICT_ERROR(1400,"请求地图错误"),
    DRIVER_CAR_BIND_EXISTS(1500,"绑定关系已存在"),
    DRIVER_CAR_BIND_NOT_EXISTS(1501,"绑定关系不存在"),
    DRIVER_BIND_EXISTS(1502,"司机已被绑定"),
    CAR_BIND_EXISTS(1503,"车辆已被绑定"),
    DRIVER_NOT_EXISTS(1504,"司机不存在"),
    SERVICE_EXISTS(1600,"服务已存在"),
    TERMINAL_EXISTS(1601,"终端已存在"),
    ORDER_GOING_ON(1701,"有进行中的订单"),
    ORDER_CANCEL_FAIL(1702,"不允许取消"),
    DEVICE_IS_BLACK(1702,"超过下单次数"),
    CITY_SERVICE_NOT_EXISTS(1703,"当前城市未开通服务"),
    CITY_DRIVER_EMPTY(1704,"当前城市无可用司机"),
    AVAILABLE_DRIVER_EMPTY(1705,"无可用司机");


    @Getter
    private int code;
    @Getter
    private String message;

}
