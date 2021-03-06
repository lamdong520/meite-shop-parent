package com.cn.mp.handler;

import com.cn.feign.MembersServiceFeign;
import com.cn.mp.builder.TextBuilder;
import com.cn.utils.base.BaseResponse;
import com.cn.utils.constant.Constants;
import com.cn.utils.utils.RedisUtil;
import com.cn.utils.utils.RegexUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MembersServiceFeign membersServiceFeign;

    /**
     * 发送验证码消息
     */
    @Value("${mayikt.weixin.registration.code.message}")
    private String registrationCodeMessage;
    /**
     * 默认回复消息
     */
    @Value("${mayikt.weixin.default.registration.code.message}")
    private String defaultRegistrationCodeMessage;


    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        //1 获取微信客户端发送的消息
        String fromContent = wxMessage.getContent();

        // 2使用正则表达式验证手机号格式
        if(RegexUtils.checkMobile(fromContent)){
            //验证手机号是否注册过，如果注册过返回提示手机号已注册
            //调用会员接口，根据手机号查询用户是否存在
            BaseResponse response = membersServiceFeign.existMobile(fromContent);
            if(response.getCode().equals(Constants.HTTP_RES_CODE_200)){
                return new TextBuilder().build("该手机号码" + fromContent + "已注册，请换一个手机号!", wxMessage, weixinService);
            }
            if(!response.getCode().equals(Constants.HTTP_RES_CODE_EXISTMOBILE_202)){
                return new TextBuilder().build(response.getMsg(), wxMessage, weixinService);
            }
            //3随机生成四位数验证码返回
            int code = registCode();
            String content = registrationCodeMessage.format(registrationCodeMessage, code);
            redisUtil.setString(Constants.WEIXINCODE_KEY+fromContent, code+"", Constants.WEIXINCODE_TIMEOUT);
            return new TextBuilder().build(content, wxMessage, weixinService);
        }
        // 返回默认接口消息
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);
    }

    // 获取注册码
    private int registCode() {
        int registCode = (int) (Math.random() * 9000 + 1000);
        return registCode;
    }


}
