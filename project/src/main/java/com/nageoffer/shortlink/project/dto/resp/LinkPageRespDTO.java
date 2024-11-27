package com.nageoffer.shortlink.project.dto.resp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LinkPageRespDTO {
    /**
     * 短链接id
     * */
    private Long id;
    /**
     * 短链接域名
     * */
    private String domain;
    /**
     * 短链接资源
     * */
    private String shortUri;
    /**
     * 短链接
     * */
    private String fullShortUrl;
    /**
     * 原始连接
     * */
    private String originUrl;
    /**
     * 点击量
     * */
    private Integer clickNum;
    /**
     * 短链接分组id
     * */
    private String gid;
    /**
     * 有效期类型 0：永久有效 1：用户自定义
     * */
    private Integer validDateType;
    /**
     * 有效期
     * */
    private LocalDateTime validDate;
    /**
     * 描述
     */
    private String description;
    /**
     * 图标url
     * */
    private String favicon;
}
