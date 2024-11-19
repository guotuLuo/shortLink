package com.nageoffer.shortlink.admin.controller;

import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.common.convention.result.Results;
import com.nageoffer.shortlink.admin.dto.req.GroupSaveNameReqDTO;
import com.nageoffer.shortlink.admin.dto.resp.GroupQueryRespDTO;
import com.nageoffer.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接分组控制层
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shortlink/v1/group")
public class GroupController {
    private final GroupService groupService;

    /**
     * 新增分组
     * */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody GroupSaveNameReqDTO groupSaveNameReqDTO){
        groupService.save(groupSaveNameReqDTO.getName());
        return Results.success();
    }

    /**
     * 查询分组
     */
    @GetMapping("/query")
    public Result<List<GroupQueryRespDTO>> query(){
        return Results.success(groupService.queryList());
    }
}
