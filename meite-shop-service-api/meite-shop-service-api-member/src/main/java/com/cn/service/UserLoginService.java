package com.cn.service;

import com.cn.input.UserLoginInDTO;
import com.cn.utils.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "用户登录服务接口")
public interface UserLoginService {

    @PostMapping("user/login")
    @ApiOperation(value = "用户登录接口")
    BaseResponse login(@RequestBody UserLoginInDTO loginInDTO);

}
