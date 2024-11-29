package com.nageoffer.shortlink.admin.remote.dto.req;
import lombok.Data;

@Data
public class LinkPageReqDTO {
    /**
     * 短链接分组id
     * */
    private String gid;
    //这里继承泛型就不用再写pageSize和pageNo
}
