package com.fran.service;

import com.fran.dto.CommonResult;
import com.fran.pojo.PriceRule;


public interface PriceRuleService {
    public CommonResult add(PriceRule priceRule);
    public CommonResult edit(PriceRule priceRule);
    public CommonResult<PriceRule> getLatest(String fareType);
    public CommonResult<Boolean> isLatest(String fareType,Integer fareVersion);
}
