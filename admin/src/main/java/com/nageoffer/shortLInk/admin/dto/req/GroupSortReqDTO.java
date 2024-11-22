package com.nageoffer.shortlink.admin.dto.req;

import lombok.Data;

@Data
public class GroupSortReqDTO {
    /**
     * 分组标识
     * */
    private String gid;
    /**
     * 分组名称
     * */
    private Integer sortOrder;
}
