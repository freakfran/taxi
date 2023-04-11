package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.PriceRule;
import com.fran.service.PriceRuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/price_rule")
public class PriceRuleController {
    @Autowired
    private PriceRuleServiceImpl priceRuleService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody PriceRule priceRule){
        return priceRuleService.add(priceRule);
    }

    @PostMapping("/edit")
    public CommonResult edit(@RequestBody PriceRule priceRule){
        return priceRuleService.edit(priceRule);
    }

}
