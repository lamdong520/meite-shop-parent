package com.cn.service;

import com.cn.entity.AppEntity;
import com.cn.utils.base.BaseApiService;
import com.cn.utils.base.BaseResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatServiceImpl extends BaseApiService implements IWechatService {
    @Override
    public BaseResponse getApp() {
        AppEntity appEntity = new AppEntity();
        appEntity.setAppId("987654321");
        appEntity.setAppName("wechat");
        return setResultSuccess(appEntity);
    }
}
