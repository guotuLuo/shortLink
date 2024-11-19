package com.nageoffer.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nageoffer.shortlink.admin.common.datbase.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_user")
public class UserDO extends BaseDO {
    /**
     *  id
     */
    private Long id;
    /**
     *  姓名
     */
    private String username;
    /**
     * 密码
     * */
    private String password;
    /**
     *  真实姓名
     */
    private String realName;
    /**
     *  手机号码
     */
    private String phone;
    /**
     *  邮箱地址
     */
    private String mail;
    /**
     *  注销时间戳
     */
    private Long deleteTime;
}
