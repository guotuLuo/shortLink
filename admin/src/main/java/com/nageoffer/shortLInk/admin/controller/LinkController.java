package com.nageoffer.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.remote.dto.LinkRemoteService;
import com.nageoffer.shortlink.admin.remote.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.admin.remote.dto.req.LinkPageReqDTO;
import com.nageoffer.shortlink.admin.remote.dto.resp.LinkCreateRespDTO;
import com.nageoffer.shortlink.admin.remote.dto.resp.LinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {
    /**
     * 后续重构为springcloud
     * */
    static LinkRemoteService linkRemoteService = new LinkRemoteService() {
    };

    /**
     * 创建短链接
     * */

    @PostMapping("/api/shortlink/project/v1/admin/create")
    public Result<LinkCreateRespDTO> create(@RequestBody LinkCreateReqDTO linkCreateReqDTO){
        return linkRemoteService.create(linkCreateReqDTO);
    }

    /**
     * 分页查询短链接
     * */
    @GetMapping("/api/shortlink/project/v1/admin/page")
    public Result<IPage<LinkPageRespDTO>> pageQuery(LinkPageReqDTO linkPageReqDTO){
        return linkRemoteService.pageQuery(linkPageReqDTO);
    }
}
