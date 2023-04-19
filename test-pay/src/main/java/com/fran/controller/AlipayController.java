package com.fran.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
@ResponseBody
public class AlipayController {
    @GetMapping("/pay")
    public String pay(String subject,String outTradeNo,String totalAmount){
        AlipayTradePagePayResponse response = null;
        try {
            response = Factory.Payment.Page().pay(subject,outTradeNo,totalAmount,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    @PostMapping("/notify")
    public String notify(HttpServletRequest request) throws Exception {
        String tradeStatus = request.getParameter("trade_status");
        if(tradeStatus.trim().equals("TRADE_SUCCESS")){//如果交易成功
            Map<String,String> parameter = new HashMap<>();
            Map<String,String[]> parameterMap = request.getParameterMap();
            for (String param:parameterMap.keySet()) {
                parameter.put(param,request.getParameter(param));
            }

            if(Factory.Payment.Common().verifyNotify(parameter)){
                System.out.println("验证通过");
                for (String name:parameter.keySet()) {
                    System.out.println(name+":"+parameter.get(name));
                }
            }else {
                System.out.println("验证不通过");
            }
        }
        return "success";
    }
}
