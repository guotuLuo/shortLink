package com.nageoffer.shortlink.project.controller;

import com.nageoffer.shortlink.project.common.convention.result.Result;
import com.nageoffer.shortlink.project.common.convention.result.Results;
import com.nageoffer.shortlink.project.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.project.dto.resp.LinkCreateRespDTO;
import com.nageoffer.shortlink.project.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shortlink/project/v1/link")
public class LinkController {
    private final LinkService linkService;

    @PostMapping("/create")
    public Result<LinkCreateRespDTO> create(@RequestBody LinkCreateReqDTO linkCreateReqDTO){
        return Results.success(linkService.create(linkCreateReqDTO));
    }
}
