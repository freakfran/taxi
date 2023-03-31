package com.fran.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DicDistrict {
    private Integer id;
    private String addressCode;
    private String addressName;
    private String parentAddressCode;
    private Integer level;
}
