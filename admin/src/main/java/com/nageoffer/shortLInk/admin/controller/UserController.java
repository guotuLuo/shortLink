package com.nageoffer.shortlink.admin.controller;

import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.common.convention.result.Results;
import com.nageoffer.shortlink.admin.dto.resp.UserActualRespDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserRespDTO;
import com.nageoffer.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shortlink/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户返回实体
     * */
    @GetMapping("/{username}")
    public Result<UserRespDTO> getUserByUserName(@PathVariable("username") String username){
        return Results.success(userService.getByUserName(username));
    }

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户返回实体
     * */
    @GetMapping("/actual/{username}")
    public Result<UserActualRespDTO> getActualUserByUserName(@PathVariable("username") String username){
        UserActualRespDTO userActualRespDTO = new UserActualRespDTO();
        BeanUtils.copyProperties(userService.getByUserName(username), userActualRespDTO);
        return Results.success(userActualRespDTO);
    }
    /**
     * 查询用户名是否可用
     * @return 返回布尔值
     */
    @GetMapping("/availableUsername")
    public Result<Boolean> availableUserName(@RequestParam("username") String username){
        return Results.success(userRegisterCachePenetrationBloomFilter.contains(username));
    }
}
