package com.lg.auth.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.auth.model.entity.Auth;
import com.lg.auth.model.param.LoginParam;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
public interface AuthService extends IService<Auth> {


    SaTokenInfo login(LoginParam loginParam);
}
