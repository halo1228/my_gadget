package com.lg.sys.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author liuga
 * @since 2023-04-19 16:14
 * Description:
 */
@Getter
@Setter
@ToString
@ApiModel
public class UserPassDTO {
    @ApiModelProperty(value = "id", required = true)
    private String id;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 密码
     */
    @ApiModelProperty(value = "新密码",required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPass;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "账号",required = true)
    @NotBlank(message = "账号不能为空")
    private String access;

}
