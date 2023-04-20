package com.lg.auth.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.auth.model.entity.Auth;
import com.lg.auth.model.dto.LoginDTO;
import com.lg.auth.model.vo.AuthVo;
import com.lg.auth.model.vo.ValidCodeVO;
import com.lg.common.pojo.ApiResult;
import com.sun.org.apache.bcel.internal.generic.PUSH;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
public interface AuthService extends IService<Auth> {

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    SaTokenInfo login(LoginDTO loginDTO);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    AuthVo getInfoById(String id);

    /**
     * 创建验证码
     *
     * @return
     */
    public ValidCodeVO createValidCode();
}
