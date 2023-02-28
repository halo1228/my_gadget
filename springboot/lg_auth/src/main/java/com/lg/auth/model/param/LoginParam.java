package com.lg.auth.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author liuga
 * @since 2023-02-14 16:27
 * Description:
 */
@Data
@ApiModel
public class LoginParam {
    @ApiModelProperty(value = "账号", required = true, position = 2)
    @NotBlank(message = "账号不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true, position = 2)
    @NotBlank(message = "密码不能为空")
    private String password;
}
