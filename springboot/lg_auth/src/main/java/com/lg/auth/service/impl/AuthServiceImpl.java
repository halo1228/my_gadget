package com.lg.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.auth.mapper.AuthMapper;
import com.lg.auth.model.entity.Auth;
import com.lg.auth.model.param.LoginParam;
import com.lg.auth.service.AuthService;
import com.lg.common.exception.CommonException;
import com.lg.common.utils.SmCryptoUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录、授权 服务实现类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {


    @Override
    public SaTokenInfo login(LoginParam loginParam) {
        LambdaQueryWrapper<Auth> wrapper = new LambdaQueryWrapper<>();
        //根据账号 查询当前用户信息
        wrapper.select(Auth::getId, Auth::getAccess, Auth::getPassword, Auth::getIsLock, Auth::getStatus);
        wrapper.eq(Auth::getAccess, loginParam.getUsername());
        Auth one = getOne(wrapper);
        //判断用户是否存在
        if (ObjectUtils.isEmpty(one)) {
            throw new CommonException("账号不存在！");
        }
        if (one.getStatus() != 0 || one.getIsLock() != 1) {
            throw new CommonException("账号被锁定或已停用！");
        }
        //校验密码
        if (!SmCryptoUtil.sm4Encrypt(loginParam.getPassword()).equals(one.getPassword())) {
            throw new CommonException("密码不正确！");
        }
        //登录
        StpUtil.login(one.getId());
        return StpUtil.getTokenInfo();
    }
}
