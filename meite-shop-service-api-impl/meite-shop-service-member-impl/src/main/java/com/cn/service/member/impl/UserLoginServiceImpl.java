package com.cn.service.member.impl;

import com.alibaba.fastjson.JSONObject;
import com.cn.input.UserLoginInDTO;
import com.cn.mapper.UserMapper;
import com.cn.mapper.UserTokenMapper;
import com.cn.mapper.entity.UserDO;
import com.cn.mapper.entity.UserTokenDo;
import com.cn.service.UserLoginService;
import com.cn.utils.base.BaseApiService;
import com.cn.utils.base.BaseResponse;
import com.cn.utils.constant.Constants;
import com.cn.utils.utils.GenerateToken;
import com.cn.utils.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginServiceImpl extends BaseApiService implements UserLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenerateToken generateToken;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public BaseResponse login(@RequestBody UserLoginInDTO userLoginInpDTO) {
        //参数验证
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }
        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        //密码加密
        String newPassWord = MD5Util.MD5(password);
        //手机号密码查询用户信息
        UserDO userDO = userMapper.getLoginUser(mobile, newPassWord);
        if(userDO == null){
            return setResultError("用户名或密码错误");
        }
        //唯一登录，根据用户id和登录类型查询用户是否登录过
        Long userId = userDO.getUserid();
        UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
        //如果查询到已经登录过修改之前的值，删除之前Redis插入新的usertoken
        if(userTokenDo != null){
            int resultCount = userTokenMapper.updateTokenAvailability(userId, loginType);
            if(resultCount>0){
                String token = userTokenDo.getToken();
                generateToken.removeToken(token);
            }
        }

        String prefix = Constants.MEMBER_TOKEN_KEYPREFIX +"."+loginType+".";
        //登录成功后生成token令牌返回
        String loginToken = generateToken.createToken(prefix, userId+"", Constants.TOKEN_MEMBER_TIME);
        JSONObject data = new JSONObject();
        data.put("userToken", loginToken);
        //保存到数据库中
        UserTokenDo userToken = new UserTokenDo();
        userToken.setUserId(userId);
        userToken.setLoginType(userLoginInpDTO.getLoginType());
        userToken.setToken(loginToken);
        userToken.setDeviceInfor(deviceInfor);
        userTokenMapper.insertUserToken(userToken);
        return setResultSuccess(data);
    }
}
