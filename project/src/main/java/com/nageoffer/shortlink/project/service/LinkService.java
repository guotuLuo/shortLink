package com.nageoffer.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shortlink.project.dao.entity.LinkDO;
import com.nageoffer.shortlink.project.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkCreateRespDTO;

public interface LinkService extends IService<LinkDO>{

    LinkCreateRespDTO create(LinkCreateReqDTO linkCreateReqDTO);
}
