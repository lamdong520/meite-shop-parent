package com.cn.service.member.impl;

import com.cn.mapper.UserMapper;
import com.cn.mapper.entity.UserDO;
import com.cn.output.UserOutDTO;
import com.cn.service.IMemberService;
import com.cn.utils.base.BaseApiService;
import com.cn.utils.base.BaseResponse;
import com.cn.utils.constant.Constants;
import com.cn.utils.utils.MeiteBeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<UserOutDTO> existMobile(String mobile) {
        //参数校验
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号不能为空!");
        }
        UserDO userDO = userMapper.existMobile(mobile);
        if(userDO ==null){
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_202, "用户不存在");
        }
        UserOutDTO userOutDTO = MeiteBeanUtils.doToDto(userDO, UserOutDTO.class);
        //userEntity.setPassword(null);
        return setResultSuccess(userOutDTO);
    }
}
