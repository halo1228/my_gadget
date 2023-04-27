package com.lg.sys.model.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liuga
 * @since 2023-04-14 10:40
 * Description:
 */
@Getter
@Setter
public class UserDetailVO {
    @ApiModelProperty(value = "userId")
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "头像URL")
    private String avatar;
    @ApiModelProperty(value = "员工编号")
    private String userCode;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
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
    private String email;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介")
    private String description;

    /**
     * 状态 0:正常
     */
    @ApiModelProperty(value = "员工状态")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
     * 序号、排序
     */
    @ApiModelProperty(value = "序号、排序")
    private Integer idx;

    /****
     * 组织数据
     */
    @ApiModelProperty(value = "所属组织ID")
    private String deptId;

    @ApiModelProperty(value = "组织名")
    private String deptName;


}
