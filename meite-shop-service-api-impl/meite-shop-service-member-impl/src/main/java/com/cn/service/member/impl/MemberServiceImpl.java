package com.cn.service.member.impl;

import com.cn.entity.UserEntity;
import com.cn.mapper.UserMapper;
import com.cn.service.IMemberService;
import com.cn.utils.base.BaseApiService;
import com.cn.utils.base.BaseResponse;
import com.cn.utils.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<UserEntity> existMobile(String mobile) {
        //参数校验
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号不能为空!");
        }
        UserEntity userEntity = userMapper.existMobile(mobile);
        if(userEntity ==null){
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        userEntity.setPassword(null);
        return setResultSuccess(userEntity);
    }
}
