package com.nageoffer.shortlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class LinkCreateReqDTO {
    /**
     * 短链接域名
     * */
    private String domain;
    /**
     * 原始连接
     * */
    private String originUrl;
    /**
     * 短链接分组id
     * */
    private String gid;
    /**
     * 创建类型 0：控制台 1：接口
     * */
    private Integer createdType;
    /**
     * 有效期类型 0：永久有效 1：用户自定义
     * */
    private Integer validDateType;
    /**
     * 有效期
     * */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime validTime;
    /**
     * 描述
     */
    private String description;
}
