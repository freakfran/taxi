package com.fran.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class DriverUser {
    private Long id;
    private String address;
    private String driverName;
    private String driverPhone;
    private Integer driverGender;
    private LocalDate driverBirthday;
    private String driverNation;
    private String driverContactAddress;
    private String licenseId;
    private LocalDate getDriverLicenseDate;
    private LocalDate driverLicenseOn;
    private LocalDate driverLicenseOff;
    private Integer taxiDriver;
    private String networkCarIssueOrganization;
    private String cerificateNo;
    private Date networkCarIssueDate;
    private LocalDate getNetworkCarProofDate;
    private LocalDate getNetworkCarProofOn;
    private LocalDate getNetworkCarProofOff;
    private LocalDate registerDate;
    private Integer commercialType;
    private String contractCompany;
    private LocalDate contractOn;
    private LocalDate contractOff;
    private Integer state;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

//    public static void main(String[] args) {
//        DriverUser driverUser = new DriverUser();
//        String s = JSON.toJSONString(driverUser,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero);
//        System.out.println(s);
//    }
}
