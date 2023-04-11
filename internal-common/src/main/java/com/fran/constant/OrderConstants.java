package com.fran.constant;

public class OrderConstants {
    /**
     * 订单状态0:订单无效 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消'
     */
    public static final int ORDER_INVALID = 0;
    public static final int ORDER_START = 1;
    public static final int DRIVER_RECEIVE_ORDER = 2;
    public static final int DRIVER_TO_PICK_UP_PASSENGER = 3;
    public static final int DRIVER_ARRIVED_DEPARTURE = 4;
    public static final int PICK_UP_PASSENGER = 5;
    public static final int PASSENGER_GET_OFF = 6;
    public static final int TO_START_PAY = 7;
    public static final int SUCCESS_PAY = 8;
    public static final int ORDER_CANCEL = 9;
}
