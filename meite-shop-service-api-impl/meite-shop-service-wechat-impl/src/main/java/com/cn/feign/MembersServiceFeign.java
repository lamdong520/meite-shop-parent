package com.cn.feign;


import com.cn.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("app-member")
public interface MembersServiceFeign extends IMemberService{
}
