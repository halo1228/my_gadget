package com.lg.sys.model.dto.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class DeptAddDTO {

    private static final long serialVersionUID = 1L;


    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名", required = true)
    @NotBlank(message = "组织名不能为空")
    @Size(max = 100, message = "组织名最大长度为100")
    private String deptName;

    /**
     * 组织编号
     */
    @ApiModelProperty(value = "组织编号")
    @Size(max = 50, message = "组织编号最大长度为50")
    private String deptCode;

    /**
     * 组织类型
     */
    @ApiModelProperty(value = "组织类型")
    @Max(value = 2, message = "组织类型最大长度为2")
    private Integer deptType;

    /**
     * 父组织id
     */
    @ApiModelProperty(value = "父组织id")
    @Size(max = 50, message = "父组织id最大长度为50")
    private String parentId;

    /**
     * 组织信息描述
     */
    @ApiModelProperty(value = "组织信息描述")
    @Size(max = 200, message = "组织信息描述最大长度为200")
    private String description;

    /**
     * 序号、排序
     */
    @ApiModelProperty(value = "序号、排序")
    private Integer idx;

}
