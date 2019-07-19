package com.cn.mapper;

import com.cn.input.UserInDTO;
import com.cn.input.UserLoginInDTO;
import com.cn.mapper.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Insert("INSERT INTO meite_user VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
    int register(UserInDTO userEntity);

    @Select("SELECT * FROM meite_user WHERE MOBILE=#{mobile};")
    UserDO existMobile(@Param("mobile") String mobile);

    @Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID "
            + "  FROM meite_user  WHERE MOBILE=#{mobile} and password=#{password};")
    UserDO getLoginUser(@Param("mobile") String mobile, @Param("password") String password);

}
