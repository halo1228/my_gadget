package com.lg.auth.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuga
 * @since 2023-02-24 17:00
 * Description:
 */
@Getter
@Setter
public class AuthVo {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "头像URL")
    private String avatar;
    @ApiModelProperty(value = "账号")
    private String access;

    //下面还有一大堆 例如权限 菜单等
}
