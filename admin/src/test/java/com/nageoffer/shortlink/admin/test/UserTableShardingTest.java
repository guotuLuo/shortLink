package com.nageoffer.shortlink.admin.test;

import org.junit.jupiter.api.Test;

public class UserTableShardingTest {
    private final static String sql = "CREATE TABLE `t_user_%d`  (\n" +
            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID\\r\\n',\n" +
            "  `username` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',\n" +
            "  `password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',\n" +
            "  `real_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',\n" +
            "  `phone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',\n" +
            "  `mail` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',\n" +
            "  `delete_time` bigint NULL DEFAULT NULL COMMENT '注销时间戳',\n" +
            "  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',\n" +
            "  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',\n" +
            "  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识0， 未删除标识1',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `idx_unique_username`(`username` ASC) USING BTREE COMMENT '用户名唯一索引，数据库兜底'\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1857260995612958723 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;\n" +
            "\n" +
            "SET FOREIGN_KEY_CHECKS = 1;";
    @Test
    public void createShardingTestTable(){
        for (int i = 0; i < 16; i++) {
            System.out.printf(sql + "%n", i);
        }
    }
}