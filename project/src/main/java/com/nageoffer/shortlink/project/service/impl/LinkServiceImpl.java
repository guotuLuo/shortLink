package com.nageoffer.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shortlink.project.dao.entity.LinkDO;
import com.nageoffer.shortlink.project.dao.mapper.LinkMapper;
import com.nageoffer.shortlink.project.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkCreateRespDTO;
import com.nageoffer.shortlink.project.service.LinkService;
import com.nageoffer.shortlink.project.toolkit.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, LinkDO> implements LinkService {
    @Override
    public LinkCreateRespDTO create(LinkCreateReqDTO linkCreateReqDTO) {
        String shortLinkSuffix = generateSuffix(linkCreateReqDTO.getOriginUrl());
        LinkDO linkDO = BeanUtil.toBean(linkCreateReqDTO, LinkDO.class);
        linkDO.setDomain(linkCreateReqDTO.getDomain());
        linkDO.setCreatedType(0);
        linkDO.setValidDateType(0);
        linkDO.setFullShortUrl(linkCreateReqDTO.getDomain() + "/" + shortLinkSuffix);
        linkDO.setShortUri(shortLinkSuffix);
        baseMapper.insert(linkDO);
        return LinkCreateRespDTO
                .builder()
                .fullShortUrl(linkDO.getFullShortUrl())
                .originUrl(linkCreateReqDTO.getOriginUrl())
                .gid(linkCreateReqDTO.getGid())
                .build();
    }

    public String generateSuffix(String originUrl){
        return HashUtil.hashToBase62(originUrl);
    }
}
