package com.lg.controller.auth;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.lg.auth.model.dto.LoginDTO;
import com.lg.auth.service.AuthService;
import com.lg.common.pojo.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author liuga
 * @since 2023-02-14 14:55
 * Description:登录、注销管理
 */
@RestController
@RequestMapping("/login")
@Api(tags = "用户登录相关接口")
@Slf4j
public class LoginController {

    @Resource
    AuthService authService;

    /**
     * 登录  ---- <a href="http://localhost:8080/login/doLogin?name=zhang&pwd=123456">...</a>
     */
    @PostMapping("doLogin")
    @ApiOperation("登录")
    @ResponseBody
    public ApiResult<?> doLogin(@RequestBody @Valid LoginDTO loginDTO) {
        SaTokenInfo token = authService.login(loginDTO);
        if (token.getIsLogin()) {
            return ApiResult.data(token);
        }
        return ApiResult.error("登录失败");

    }

    /**
     * 注销
     */
    @RequestMapping("logout")
    public ApiResult<?> logout() {
        StpUtil.logout();
        return ApiResult.ok();
    }

    /**
     * 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
     */
    @RequestMapping("getLoginId")
    public ApiResult<?> getLoginId() {
        Object loginId = StpUtil.getLoginId();
        return ApiResult.data(loginId);
    }
}
