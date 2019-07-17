package com.cn.service;

import com.cn.entity.UserEntity;
import com.cn.utils.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "会员服务接口")
public interface IMemberService {

    @ApiOperation(value = "根据手机号查询用户信息")
    @ApiImplicitParam(name = "mobile", dataType = "String", required = true, value = "手机号码")
    @GetMapping("/user/exist")
    BaseResponse<UserEntity> existMobile(@RequestParam("mobile") String mobile);

}
