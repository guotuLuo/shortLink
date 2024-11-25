package com.nageoffer.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LinkCreateRespDTO {
    /**
     * 短链接分组id
     * */
    private String gid;
    /**
     * 短链接
     * */
    private String fullShortUrl;
    /**
     * 原始连接
     * */
    private String originUrl;
}
