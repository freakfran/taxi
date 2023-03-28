package com.fran.dto;

import com.fran.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommonResult<T>{
    private int code;
    private String message;
    private T data;

    public static CommonResult success(){
        return new CommonResult().setCode(CommonStatusEnum.SUCCESS.getCode()).
                setMessage(CommonStatusEnum.SUCCESS.getMessage());
    }
    public static <T>CommonResult success(T data){
        return new CommonResult().setCode(CommonStatusEnum.SUCCESS.getCode()).
                setMessage(CommonStatusEnum.SUCCESS.getMessage()).
                setData(data);
    }

    public static CommonResult fail(int code,String message){
        return new CommonResult().setCode(code).
                setMessage(message);
    }

    public static CommonResult fail(int code,String message,String data){
        return new CommonResult().setCode(code).
                setMessage(message)
                .setData(data);
    }

    public static <T>CommonResult fail(T data){
        return new CommonResult().setData(data);
    }

}
