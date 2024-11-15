package com.nageoffer.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

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
    /**
     *  创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     *  更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     *  删除软标签
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
