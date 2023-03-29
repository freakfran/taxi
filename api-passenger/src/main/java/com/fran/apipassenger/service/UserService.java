package com.fran.apipassenger.service;

import com.fran.dto.CommonResult;

public interface UserService {

    public CommonResult getUserByAccessToken(String accessToken);
}
