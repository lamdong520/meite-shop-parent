package com.cn.service.member.impl;

import com.cn.feign.VerificaCodeServiceFeign;
import com.cn.input.UserInDTO;
import com.cn.mapper.UserMapper;
import com.cn.service.MemberRegisterService;
import com.cn.utils.base.BaseApiService;
import com.cn.utils.base.BaseResponse;
import com.cn.utils.constant.Constants;
import com.cn.utils.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRegisterServiceImpl extends BaseApiService implements MemberRegisterService {

    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse register(@RequestBody UserInDTO userInDTO, String registCode) {
        //验证参数
        String userName = userInDTO.getUserName();
        String mobile = userInDTO.getMobile();
        if(StringUtils.isEmpty(userName)){
            return setResultError("用户名为空");
        }
        if(StringUtils.isEmpty(mobile)){
            return setResultError("请输入手机号");
        }
        //密码不能为空。。。两次密码相同。。。。
        //请输入验证码。。。
        //密码加密
        String newpassWord = MD5Util.MD5(userInDTO.getPassword());
        userInDTO.setPassword(newpassWord);

        //验证输入的注册码是否正确 调用微信服务注册码接口
//        BaseResponse response = verificaCodeServiceFeign.verificaWeixinCode(mobile, registCode);
//        if(!response.getCode().equals(Constants.HTTP_RES_CODE_200)){
//            return setResultError(response.getMsg());
//        }
        //保存数据库
        int result = userMapper.register(userInDTO);
        return result > 0 ? setResultSuccess("注册成功") : setResultError("注册失败，请联系管理员");
    }
}
