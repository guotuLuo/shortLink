package com.nageoffer.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nageoffer.shortlink.project.dao.entity.LinkDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LinkPageReqDTO extends Page<LinkDO> {
    /**
     * 短链接分组id
     * */
    private String gid;
    //这里继承泛型就不用再写pageSize和pageNo
}
