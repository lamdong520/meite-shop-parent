package com.cn.feign;

import com.cn.service.IWechatService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-wechat")
public interface WechatFeign extends IWechatService{
}
