package com.cn.service;

import com.cn.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "会员服务接口")
public interface IMemberService {

    @ApiOperation(value = "会员服务调用微信服务获取微信app")
    @GetMapping("/memberToWechat")
    public AppEntity memberToWechat();
}
