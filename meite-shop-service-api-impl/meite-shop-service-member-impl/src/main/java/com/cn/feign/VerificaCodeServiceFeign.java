package com.cn.feign;

import com.cn.service.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-wechat")
public interface VerificaCodeServiceFeign extends VerificaCodeService{
}
