package com.nageoffer.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nageoffer.shortlink.project.common.convention.result.Result;
import com.nageoffer.shortlink.project.common.convention.result.Results;
import com.nageoffer.shortlink.project.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.project.dto.req.LinkPageReqDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkCreateRespDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkPageRespDTO;
import com.nageoffer.shortlink.project.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shortlink/project/v1/link")
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/create")
    public Result<LinkCreateRespDTO> create(@RequestBody LinkCreateReqDTO linkCreateReqDTO){
        return Results.success(linkService.create(linkCreateReqDTO));
    }

    @GetMapping("/page")
    public Result<IPage<LinkPageRespDTO>> pageQuery(LinkPageReqDTO linkPageReqDTO){
        return Results.success(linkService.pageQuery(linkPageReqDTO));
    }
}
