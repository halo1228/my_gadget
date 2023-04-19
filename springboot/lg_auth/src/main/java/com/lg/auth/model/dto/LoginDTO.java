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
    @ApiModelProperty(value = "账号", required = true, position = 2)
    @NotBlank(message = "账号不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true, position = 2)
    @NotBlank(message = "密码不能为空")
    private String password;
}
