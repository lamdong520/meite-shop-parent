package com.cn.service;

import com.cn.entity.UserEntity;
import com.cn.utils.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;

@Api(tags = "会员注册接口")
public interface MemberRegisterService {

    @ApiOperation(value = "会员注册接口")
    @PostMapping("/member/regist")
    BaseResponse register(UserEntity userEntity, String registCode);
}
