package com.lg.auth.enums;

import lombok.Getter;

/**
 *
 *
 * @author liuga
 * @since 2023-02-01
 **/
@Getter
public enum AuthExceptionEnum {


    /**
     * 验证码错误
     */
    VALID_CODE_ERROR("验证码错误"),

    /**
     * 账号错误
     */
    ACCESS_ERROR("账号不存在"),

    /**
     * 账号已停用
     */
    ACCESS_DISABLED("账号被锁定或已停用"),

    /**
     * 密码错误
     */
    PWD_ERROR("密码错误"),


    /**
     * 密码解密失败
     */
    PWD_DECRYPT_ERROR("密码解密失败");

    private final String value;

    AuthExceptionEnum(String value) {
        this.value = value;
    }
}
