package com.lg.auth.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuga
 * @since 2023-04-20 11:51
 * Description:
 */
@Getter
@Setter
@Builder
public class ValidCodeVO {

    /** 验证码图片，Base64 */
    @ApiModelProperty(value = "验证码图片，Base64", position = 1)
    private String validCodeBase64;

    /** 验证码请求号 */
    @ApiModelProperty(value = "验证码请求号", position = 2)
    private String validCodeReqNo;
}
