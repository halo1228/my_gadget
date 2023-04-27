package com.lg.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.auth.mapper.AuthMapper;
import com.lg.auth.model.dto.LoginDTO;
import com.lg.auth.model.entity.Auth;
import com.lg.auth.model.vo.AuthVo;
import com.lg.auth.model.vo.ValidCodeVO;
import com.lg.auth.service.AuthService;
import com.lg.common.cache.RedisUtil;
import com.lg.auth.enums.AuthExceptionEnum;
import com.lg.common.exception.CommonException;
import com.lg.common.utils.SmCryptoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    /**
     * 缓存前缀
     */
    private static final String CACHE_KEY_PREFIX = "auth:validCode:";
    @Resource
    AuthMapper authMapper;
    @Resource
    RedisUtil redisUtil;

    @Override
    public SaTokenInfo login(LoginDTO loginDTO) {
        //先验证验证码是否正确
        Object o = redisUtil.get(CACHE_KEY_PREFIX + loginDTO.getValidCodeReqNo());
        if (!loginDTO.getValidCode().equals(o)) {
            throw new CommonException(AuthExceptionEnum.VALID_CODE_ERROR.getValue());
        }
        LambdaQueryWrapper<Auth> wrapper = new LambdaQueryWrapper<>();
        //根据账号 查询当前用户信息
        wrapper.select(Auth::getId, Auth::getAccess, Auth::getPassword, Auth::getIsLock, Auth::getStatus);
        wrapper.eq(Auth::getAccess, loginDTO.getUsername());
        Auth one = getOne(wrapper);
        //判断用户是否存在
        if (ObjectUtils.isEmpty(one)) {
            throw new CommonException(AuthExceptionEnum.ACCESS_ERROR.getValue());
        }
        //校验账号状态
        if (one.getStatus() != 0 || one.getIsLock() != 1) {
            throw new CommonException(AuthExceptionEnum.ACCESS_DISABLED.getValue());
        }
        //加解密密码
        String pwd;
        try {
            pwd = SmCryptoUtil.doHashValue(SmCryptoUtil.sm2Decrypt(loginDTO.getPassword()));
        } catch (Exception e) {
            throw new CommonException(AuthExceptionEnum.PWD_DECRYPT_ERROR.getValue());
        }
        //校验密码
        if (!one.getPassword().equals(pwd)) {
            throw new CommonException(AuthExceptionEnum.PWD_ERROR.getValue());
        }
        //登录
        StpUtil.login(one.getId());
        //登录成功 删除此二维码缓存
        redisUtil.del(CACHE_KEY_PREFIX + loginDTO.getValidCodeReqNo());
        return StpUtil.getTokenInfo();
    }


    @Override
    public AuthVo getInfoById(String id) {
        /*
         * 2，获取用户角色、权限数据
         * 3、获取用户菜单数据 动态路由
         */
        return authMapper.getUserInfo(id);
    }

    @Override
    public ValidCodeVO createValidCode() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(100, 38, 4, 10);
        // 获取验证码的值
        String validCode = circleCaptcha.getCode();
        // 获取验证码的base64
        String validCodeBase64 = circleCaptcha.getImageBase64Data();
        // 生成请求号
        String validCodeReqNo = IdWorker.getIdStr();
        redisUtil.set(CACHE_KEY_PREFIX + validCodeReqNo, validCode, 5 * 60);
        return ValidCodeVO.builder().validCodeBase64(validCodeBase64).validCodeReqNo(validCodeReqNo).build();
    }


}
