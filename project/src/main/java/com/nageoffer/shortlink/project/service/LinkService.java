package com.nageoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shortlink.project.dao.entity.LinkDO;
import com.nageoffer.shortlink.project.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.project.dto.req.LinkPageReqDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkCreateRespDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkPageRespDTO;

public interface LinkService extends IService<LinkDO>{

    LinkCreateRespDTO create(LinkCreateReqDTO linkCreateReqDTO);

    IPage<LinkPageRespDTO> pageQuery(LinkPageReqDTO linkPageReqDTO);
}
