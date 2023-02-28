package com.lg.sys.model.vo.user;

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
public class UserVo {
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "头像URL")
    private String avatar;
    @ApiModelProperty(value = "账号")
    private String access;

}
