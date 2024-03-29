
package com.lg.common.exception;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 通用异常
 *
 * @author liuga
 * @since 2023-02-01
 */
@Getter
@Setter
public class CommonException extends RuntimeException {

    private Integer code;

    private String msg;

    public CommonException() {
        super("服务器异常");
        this.code = 500;
        this.msg = "服务器异常";
    }

    public CommonException(String msg, Object... arguments) {
        super(StrUtil.format(msg, arguments));
        this.code = 500;
        this.msg = StrUtil.format(msg, arguments);
    }

    public CommonException(Integer code, String msg, Object... arguments) {
        super(StrUtil.format(msg, arguments));
        this.code = code;
        this.msg = StrUtil.format(msg, arguments);
    }
}
