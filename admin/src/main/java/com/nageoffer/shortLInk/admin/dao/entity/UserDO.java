package com.nageoffer.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nageoffer.shortlink.admin.common.convention.serialize.PhoneDesensitizationSerializer;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class UserDO {
    /**
     *  id
     */
    private Long id;
    /**
     *  姓名
     */
    private String userName;
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
    /**
     *  创建时间
     */
    private Date createTime;
    /**
     *  更新时间
     */
    private Date updateTime;
    /**
     *  删除软标签
     */
    private Integer delFlag;
}
