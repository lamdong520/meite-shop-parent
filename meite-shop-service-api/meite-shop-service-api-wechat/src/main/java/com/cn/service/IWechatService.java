package com.cn.service;

import com.cn.entity.AppEntity;
import com.cn.utils.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "微信服务接口")
public interface IWechatService {
    /**
     * 功能说明： 应用服务接口
     */
    @ApiOperation(value = "获取微信app")
    @GetMapping("/getApp")
    BaseResponse getApp();
}
