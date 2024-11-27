package com.nageoffer.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nageoffer.shortlink.project.common.datbase.BaseDO;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_link")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkDO extends BaseDO {
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
     * 启用标识 0：未启用 1：已启用
     * */
    private Integer enableStatus;
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
