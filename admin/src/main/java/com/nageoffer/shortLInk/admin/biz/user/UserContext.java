package com.nageoffer.shortlink.admin.biz.user;

import java.util.Optional;

public final class UserContext {
    private static final ThreadLocal<UserInfoDTO> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUser(UserInfoDTO userInfoDTO){
        USER_INFO_THREAD_LOCAL.set(userInfoDTO);
    }

    public static String getUsername(){
        UserInfoDTO userInfoDTO = USER_INFO_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getUsername).orElse(null);
    }

    public static String getId(){
        UserInfoDTO userInfoDTO = USER_INFO_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getId).orElse(null);
    }

    public static String getRealName(){
        UserInfoDTO userInfoDTO = USER_INFO_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getRealName).orElse(null);
    }


    public static void remove(){
        USER_INFO_THREAD_LOCAL.remove();
    }
}
