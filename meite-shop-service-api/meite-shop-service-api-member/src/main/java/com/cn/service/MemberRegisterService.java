package com.cn.service;

import com.cn.input.UserInDTO;
import com.cn.utils.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "会员注册接口")
public interface MemberRegisterService {

    @ApiOperation(value = "会员注册接口")
    @PostMapping("/member/regist")
    BaseResponse register(@RequestBody UserInDTO userEntity, String registCode);
}
