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
    PRICE_RULE_EMPTY(1300,"计价规则不存在");

    @Getter
    private int code;
    @Getter
    private String message;

}
