package com.fran.controller;

import com.fran.dto.CommonResult;
import com.fran.pojo.PriceRule;
import com.fran.service.PriceRuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getLatestVersion")
    public CommonResult<PriceRule> getLatest(@RequestParam String fareType){
        return priceRuleService.getLatest(fareType);
    }

    @GetMapping("/isLatest")
    public CommonResult<Boolean> isLatest(@RequestParam String fareType,@RequestParam Integer fareVersion){
        return priceRuleService.isLatest(fareType,fareVersion);
    }

    @GetMapping("/is_exists")
    public CommonResult<Boolean> isExists(@RequestBody PriceRule priceRule){
        return priceRuleService.isExists(priceRule);
    }

}
