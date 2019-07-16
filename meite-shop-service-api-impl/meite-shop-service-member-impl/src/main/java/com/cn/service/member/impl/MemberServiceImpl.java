package com.cn.service.member.impl;

import com.cn.feign.WechatFeign;
import com.cn.service.IMemberService;
import com.cn.utils.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private WechatFeign wechatFeign;

    @Override
    public BaseResponse memberToWechat() {
        //会员服务调用微信服务接口 rest feign
        return wechatFeign.getApp();
    }
}
