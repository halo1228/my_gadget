package com.lg.auth.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liuga
 * @since 2023-02-14 16:27
 * Description:
 */
@Data
@ApiModel
public class LoginDTO {
    @ApiModelProperty(value = "账号", required = true, position = 1)
    @NotBlank(message = "账号不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true, position = 2)
    @NotBlank(message = "密码不能为空")
    private String password;
    @ApiModelProperty(value = "验证码", required = true, position = 3)
    @NotBlank(message = "验证码不能为空")
    private String validCode;
    @ApiModelProperty(value = "验证码请求号", required = true, position = 4)
    @NotBlank(message = "验证码请求号不能为空")
    private String validCodeReqNo;
}
