package com.nageoffer.shortlink.admin.toolkit;

import cn.hutool.core.util.RandomUtil;

public class RandomGenerator {
    private final static String baseString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static String generateRandomGID(int length){
        return RandomUtil.randomString(baseString, length);
    }
}
