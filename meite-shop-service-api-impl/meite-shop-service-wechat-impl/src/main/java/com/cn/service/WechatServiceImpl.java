package com.cn.service;

import com.cn.entity.AppEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatServiceImpl implements IWechatService {
    @Override
    public AppEntity getApp() {
        AppEntity appEntity = new AppEntity();
        appEntity.setAppId("987654321");
        appEntity.setAppName("wechat");
        return appEntity;
    }
}
