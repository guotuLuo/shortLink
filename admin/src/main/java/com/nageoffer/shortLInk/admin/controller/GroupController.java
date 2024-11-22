package com.nageoffer.shortlink.admin.controller;

import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.common.convention.result.Results;
import com.nageoffer.shortlink.admin.dto.req.GroupSaveNameReqDTO;
import com.nageoffer.shortlink.admin.dto.req.GroupSortReqDTO;
import com.nageoffer.shortlink.admin.dto.req.GroupUpdateReqDTO;
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

    /**
     * 修改短链接分组
     * */
    @PutMapping("/update")
    public Result<Void> update(@RequestBody GroupUpdateReqDTO groupUpdateReqDTO){
        groupService.update(groupUpdateReqDTO);
        return Results.success();
    }

    /**
     * 删除短链接分组（软删除，删除标识设置为1）
     * */
    @DeleteMapping("/delete")
    public Result<Void> delete(@RequestParam String gid){
        groupService.delete(gid);
        return Results.success();
    }

    @PostMapping("/sort")
    public Result<Void> sort(@RequestBody List<GroupSortReqDTO> list){
        groupService.sort(list);
        return Results.success();
    }
}
