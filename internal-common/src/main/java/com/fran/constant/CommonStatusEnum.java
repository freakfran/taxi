package com.fran.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public enum CommonStatusEnum {
    SUCCESS(1,"success"),
    FAIL(0,"fail");

    @Getter
    private int code;
    @Getter
    private String message;

}
