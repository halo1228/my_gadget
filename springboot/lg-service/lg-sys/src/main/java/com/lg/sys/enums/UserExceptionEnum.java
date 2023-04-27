package com.lg.sys.enums;

import lombok.Getter;

/**
 * @author liuga
 * @since 2023-02-01
 **/
@Getter
public enum UserExceptionEnum {


    /**
     * 账号重复
     */
    ACCOUNT_REPEAT("账号重复"),

    /**
     * 加解密失败
     */
    PWD_DECRYPT_ERROR("加解密失败");

    private final String value;

    UserExceptionEnum(String value) {
        this.value = value;
    }
}
