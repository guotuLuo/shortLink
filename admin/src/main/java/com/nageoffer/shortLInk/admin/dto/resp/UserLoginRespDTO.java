package com.nageoffer.shortlink.admin.dto.resp;

import lombok.Data;

@Data
public class UserLoginRespDTO {
    String token;

    public UserLoginRespDTO(String token){
        this.token = token;
    }
}
