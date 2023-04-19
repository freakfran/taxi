package com.fran.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayConfig {
    private String appId;
    private String appPriateKey;
    private String publicKey;
    private String notifyURL;

    @PostConstruct
    public void  init(){
        Config config = new Config();
        //基础配置
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";
        //业务配置
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPriateKey;
        config.alipayPublicKey = this.publicKey;
        config.notifyUrl = this.notifyURL;
        Factory.setOptions(config);
    }
}
