package com.lg.exception;

import cn.dev33.satoken.exception.*;
import com.lg.common.exception.CommonException;
import com.lg.common.pojo.ApiResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author liuga
 * @date 2023/01/14 11:16
 * Description:全局异常拦截
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    public ApiResult<?> handlerNotLoginException(NotLoginException nle)
            throws Exception {

        // 打印堆栈，以供调试
        // nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return ApiResult.error(50014, message);
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public ApiResult<?> handlerException(NotPermissionException e) {
        e.printStackTrace();
        return ApiResult.error("缺少权限：" + e.getPermission());
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public ApiResult<?> handlerException(NotRoleException e) {
        e.printStackTrace();
        return ApiResult.error("缺少角色：" + e.getRole());
    }

    // 拦截：二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public ApiResult<?> handlerException(NotSafeException e) {
        e.printStackTrace();
        return ApiResult.error("二级认证校验失败：" + e.getService());
    }

    // 拦截：服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public ApiResult<?> handlerException(DisableServiceException e) {
        e.printStackTrace();
        return ApiResult.error("当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封");
    }

    // 拦截：Http Basic 校验失败异常
    @ExceptionHandler(NotBasicAuthException.class)
    public ApiResult<?> handlerException(NotBasicAuthException e) {
        e.printStackTrace();
        return ApiResult.error(e.getMessage());
    }

    /**
     * 拦截自定义异常
     */
    @ExceptionHandler(CommonException.class)
    public ApiResult<?> commonException(CommonException e) {
        e.printStackTrace();
        return ApiResult.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResult<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(n -> String.format("%s: %s", n.getField(), n.getDefaultMessage()))
                .reduce((x, y) -> String.format("%s; %s", x, y))
                .orElse("参数输入有误");
        return ApiResult.error(msg);
    }


    // 拦截：其它所有异常
    @ExceptionHandler(Exception.class)
    public ApiResult<?> handlerException(Exception e) {
        e.printStackTrace();
        // return ApiResult.error(e.getMessage());
        return ApiResult.error("系统异常！");
    }

}
