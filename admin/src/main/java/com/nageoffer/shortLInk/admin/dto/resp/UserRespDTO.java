package com.nageoffer.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nageoffer.shortlink.admin.common.convention.serialize.PhoneDesensitizationSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class UserRespDTO {
    /**
     *  id
     */
    private Long id;
    /**
     *  姓名
     */
    private String userName;
    /**
     *  真实姓名
     */
    private String realName;
    /**
     *  手机号码
     */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
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
