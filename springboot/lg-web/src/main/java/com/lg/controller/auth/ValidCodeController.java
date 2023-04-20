package com.lg.controller.auth;

import com.lg.auth.model.vo.ValidCodeVO;
import com.lg.auth.service.AuthService;
import com.lg.common.pojo.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuga
 * @since 2023-04-20 11:35
 * Description:
 */
@RestController
@RequestMapping("/code")
public class ValidCodeController {

    @Resource
    AuthService authService;

    /**
     * 生成验证码
     */
    @RequestMapping("/validCode")
    public ApiResult<ValidCodeVO> code(){
        return ApiResult.data(authService.createValidCode());
    }

}
