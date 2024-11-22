package com.nageoffer.shortlink.admin.controller;

import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.common.convention.result.Results;
import com.nageoffer.shortlink.admin.dto.req.UserLoginReqDTO;
import com.nageoffer.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.nageoffer.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserActualRespDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserRespDTO;
import com.nageoffer.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shortlink/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
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
    public Result<Boolean> isAvailableUserName(@RequestParam("username") String username){
        return Results.success(userService.isAvailableUserName(username));
    }

    /**
     * 注册用户
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestDTO){
        userService.register(requestDTO);
        return Results.success();
    }

    /**
     * 修改用户
     */
    @PutMapping("/update")
    public Result<Void>  update(@RequestBody UserUpdateReqDTO userUpdateReqDTO){
        userService.update(userUpdateReqDTO);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO){
        return Results.success(userService.login(userLoginReqDTO));
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping("/has-login")
    public Result<Boolean> login(@RequestParam("username") String username, @RequestParam("uuid") String uuid){
        return Results.success(userService.checkLogin(username, uuid));
    }

    /**
     * 用户注销
     */
    @PostMapping("/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("uuid") String uuid){
        userService.logout(username, uuid);
        return Results.success();
    }
}
