package com.fran.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PriceRule {
    private String cityCode;
    private String vehicleType;
    private Double startFare;
    private Integer startMile;
    private Integer fareVersion;
    private String fareType;
    private Double unitPricePerMile;
    private Double unitPricePerMinute;

    public static void main(String[] args) {
        PriceRule p = new PriceRule();
        String s = JSON.toJSONString(p, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
        System.out.println(s);
    }
}
