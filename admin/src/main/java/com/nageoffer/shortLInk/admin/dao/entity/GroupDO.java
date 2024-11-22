package com.nageoffer.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nageoffer.shortlink.admin.common.datbase.BaseDO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@TableName("t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupDO extends BaseDO {
    /**
     * ID
     * */
    private Long id;
    /**
     * 分组ID
     * */
    private String gid;
    /**
     * 分组名称
     * */
    private String name;
    /**
     * 创建分组用户名
     * */
    private String username;
    /**
     * 分组排序
     * */
    @TableField(fill = FieldFill.INSERT)
    private Integer sortOrder;
}
