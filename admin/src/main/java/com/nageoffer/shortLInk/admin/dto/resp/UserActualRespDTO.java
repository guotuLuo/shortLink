package com.nageoffer.shortlink.admin.dto.resp;

import lombok.Data;

import java.util.Date;

@Data
public class UserActualRespDTO {
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
