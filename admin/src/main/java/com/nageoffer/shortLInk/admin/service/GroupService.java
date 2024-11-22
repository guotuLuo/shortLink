package com.nageoffer.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shortlink.admin.dao.entity.GroupDO;
import com.nageoffer.shortlink.admin.dto.req.GroupSortReqDTO;
import com.nageoffer.shortlink.admin.dto.req.GroupUpdateReqDTO;
import com.nageoffer.shortlink.admin.dto.resp.GroupQueryRespDTO;

import java.util.List;

/**
 * 短链接分组接口层
 * */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增分组
     * */
    void save(String groupName);

    /**
     * 查询分组
     * */
    List<GroupQueryRespDTO> queryList();

    void update(GroupUpdateReqDTO groupUpdateReqDTO);

    void delete(String gid);

    void sort(List<GroupSortReqDTO> list);
}
