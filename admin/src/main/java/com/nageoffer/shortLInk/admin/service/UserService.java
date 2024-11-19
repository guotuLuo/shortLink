package com.nageoffer.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nageoffer.shortlink.admin.dao.entity.UserDO;
import com.nageoffer.shortlink.admin.dto.req.UserLoginReqDTO;
import com.nageoffer.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.nageoffer.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.nageoffer.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {
    UserRespDTO getByUserName(String username);
    Boolean isAvailableUserName(String username);

    void register(UserRegisterReqDTO requestDTO);

    void update(UserUpdateReqDTO userUpdateDTO);

    UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO);

    Boolean checkLogin(String username, String uuid);

    void logout(String username, String uuid);
}
