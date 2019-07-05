package com.cn.service.member.impl;

import com.cn.entity.AppEntity;
import com.cn.feign.WechatFeign;
import com.cn.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private WechatFeign wechatFeign;

    @Override
    public AppEntity memberToWechat() {
        //会员服务调用微信服务接口 rest feign
        AppEntity appEntity = wechatFeign.getApp();
        return appEntity;
    }
}
