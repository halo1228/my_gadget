package com.lg.sys.model.param.dept;

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
public class DeptPageParam {

    private String id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "父级部门ID", required = true)
    @Size(max = 100, message = "部门名最大长度为100")
    private String deptName;

    @ApiModelProperty(value = "父级部门ID", required = true)
    @Max(value = 50, message = "父级部门ID最大长度为50")
    private String parentId;
    private  int size;

    private  int current;
}
