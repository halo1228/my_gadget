package com.lg.sys.model.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@Getter
@Setter
@ToString
@ApiModel
public class UserPageParam {

    private String id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", required = true)
    @Size(max = 100, message = "姓名最大长度为100")
    private String name;

    /**
     * 员工编号
     */
    @ApiModelProperty(value = "员工编号")
    @Size(max = 100, message = "员工编号最大长度为100")
    private String userCode;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @Max(value = 2, message = "性别最大长度为2")
    private Integer sex;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Size(max = 13, message = "手机号最大长度为13")
    private String tel;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private Date birthday;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 100, message = "邮箱最大长度为100")
    private String email;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    @Size(max = 100, message = "地址最大长度为100")
    private String address;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像URL")
    @Size(max = 100, message = "头像URL最大长度为200")
    private String avatar;

    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介")
    @Size(max = 200, message = "个人简介最大长度为200")
    private String description;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer idx;


    @ApiModelProperty(value = "组织ID")
    private String deptId;
    private  int size;

    private  int current;
}
