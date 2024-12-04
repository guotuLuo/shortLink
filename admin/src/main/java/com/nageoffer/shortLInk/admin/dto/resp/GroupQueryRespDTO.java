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
     * 分组排序
     * */
    private Integer sortOrder;
    /**
     * 当前分组短链接数量
     * */
    private Integer shortLinkCount;
}
