package com.nageoffer.shortlink.admin.biz.user;

import lombok.Data;

@Data
public class UserInfoDTO {
    /**
     * 用户id
     * */
    private String id;
    /**
     * 用户姓名
     * */
    private String username;
    /**
     * 用户真实姓名
     * */
    private String realName;
}
