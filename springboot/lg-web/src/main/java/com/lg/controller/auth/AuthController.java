package com.lg.controller.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.lg.auth.service.AuthService;
import com.lg.common.pojo.ApiResult;
import com.lg.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author liuga
 * @since 2023-02-14 14:55
 * Description:登录管理
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "用户权限相关接口")
@Log
public class AuthController {


    @Resource
    AuthService authService;


    @Resource
    UserService userService;

    /**
     * 查询 Token 信息
     */
    @RequestMapping("token/info")
    public ApiResult<?> getTokenInfo() {
        return ApiResult.data(StpUtil.getTokenInfo());
    }


    @GetMapping("user/info")
    @ApiOperation("根据token获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", defaultValue = "", dataTypeClass = String.class),
    })
    public ApiResult<?> getUserInfo(@RequestParam @Valid String token) {
        //先根据token 获取对应的用户id
        Object loginIdByToken = StpUtil.getLoginIdByToken(token);
        return ApiResult.data(userService.getInfoById((String) loginIdByToken));
    }
}
