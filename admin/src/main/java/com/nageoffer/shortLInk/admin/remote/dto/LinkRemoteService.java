package com.nageoffer.shortlink.admin.remote.dto;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nageoffer.shortlink.admin.common.convention.result.Result;
import com.nageoffer.shortlink.admin.remote.dto.req.LinkCreateReqDTO;
import com.nageoffer.shortlink.admin.remote.dto.req.LinkPageReqDTO;
import com.nageoffer.shortlink.admin.remote.dto.resp.LinkCreateRespDTO;
import com.nageoffer.shortlink.admin.remote.dto.resp.LinkPageRespDTO;

import java.util.HashMap;
import java.util.Map;

public interface LinkRemoteService {
    default Result<LinkCreateRespDTO> create(LinkCreateReqDTO linkCreateReqDTO) {
        String result  = HttpUtil.post("http://127.0.0.1:8001/api/shortlink/project/v1/link/create", JSON.toJSONString(linkCreateReqDTO));
        return JSON.parseObject(result, new TypeReference<>() {});
    }

    default Result<IPage<LinkPageRespDTO>> pageQuery(LinkPageReqDTO linkPageReqDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("gid", linkPageReqDTO.getGid());
        map.put("current", 1);
        map.put("size", 10);
        String result  = HttpUtil.get("http://127.0.0.1:8001/api/shortlink/project/v1/link/page", map);
        return JSON.parseObject(result, new TypeReference<>() {});
    }
}
