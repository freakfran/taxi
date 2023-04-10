package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.PriceRule;
import org.springframework.web.bind.annotation.RequestBody;

public interface PriceRuleService {
    public CommonResult add(PriceRule priceRule);
}
