package com.nageoffer.shortlink.project.service.impl;

import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mchange.util.DuplicateElementException;
import com.nageoffer.shortlink.project.common.convention.exception.ServiceException;
import com.nageoffer.shortlink.project.dao.entity.LinkDO;
import com.nageoffer.shortlink.project.dao.mapper.LinkMapper;
import com.nageoffer.shortlink.project.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkCreateRespDTO;
import com.nageoffer.shortlink.project.service.LinkService;
import com.nageoffer.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LinkServiceImpl extends ServiceImpl<LinkMapper, LinkDO> implements LinkService {
    private final RBloomFilter<String> cachePenetrationBloomFilter;
    @Override
    public LinkCreateRespDTO create(LinkCreateReqDTO linkCreateReqDTO) {
        String shortLinkSuffix = generateSuffix(linkCreateReqDTO.getDomain(), linkCreateReqDTO.getOriginUrl());
        String fullShortUrl = StrBuilder
                .create(linkCreateReqDTO.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        LinkDO linkDO = LinkDO
                .builder()
                .domain(linkCreateReqDTO.getDomain())
                .originUrl(linkCreateReqDTO.getOriginUrl())
                .gid(linkCreateReqDTO.getGid())
                .createdType(linkCreateReqDTO.getCreatedType())
                .validDateType(linkCreateReqDTO.getValidDateType())
                .validDate(linkCreateReqDTO.getValidDate())
                .description(linkCreateReqDTO.getDescription())
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .fullShortUrl(fullShortUrl)
                .build();
        try{
            baseMapper.insert(linkDO);
        }catch (DuplicateKeyException ex){
            // TODO 为什么插入失败还要再查一次呢？
            LambdaQueryWrapper<LinkDO> eq = Wrappers
                    .lambdaQuery(LinkDO.class)
                    .eq(LinkDO::getFullShortUrl, fullShortUrl);
            LinkDO exist = baseMapper.selectOne(eq);
            if(exist != null){
                throw new ServiceException(String.format("短链接 %s 生成重复", fullShortUrl));
            }
        }
        cachePenetrationBloomFilter.add(fullShortUrl);
        return LinkCreateRespDTO
                .builder()
                .fullShortUrl(linkDO.getFullShortUrl())
                .originUrl(linkCreateReqDTO.getOriginUrl())
                .gid(linkCreateReqDTO.getGid())
                .build();
    }

    public String generateSuffix(String domain, String originUrl){
        String shortLinkSuffix;
        String fullShortUrl = domain + "/";
        int generateCount = 0;
        while(true){
            if(generateCount > 10){
                throw new DuplicateElementException();
            }
            // 加盐防止碰撞
            shortLinkSuffix = HashUtil.hashToBase62(originUrl + System.currentTimeMillis());
            if(!cachePenetrationBloomFilter.contains(fullShortUrl + shortLinkSuffix)){
                break;
            }
            generateCount++;
        }
        return shortLinkSuffix;
    }
}
