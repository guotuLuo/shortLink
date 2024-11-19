package com.nageoffer.shortlink.admin.dto.resp;

import lombok.Data;

@Data
public class GroupQueryRespDTO {
    /**
     * 分组ID
     * */
    private String gid;
    /**
     * 分组名称
     * */
    private String name;
    /**
     * 创建分组用户名
     * */
    private String username;
    /**
     * 分组排序
     * */
    private Integer sortOrder;
}
