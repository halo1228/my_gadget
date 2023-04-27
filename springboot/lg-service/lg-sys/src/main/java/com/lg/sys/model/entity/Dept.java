package com.lg.sys.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@Accessors(chain = true)
@TableName("sys_dept")
public class Dept extends Model<Dept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 组织名称
     */
    @TableField("DEPT_NAME")
    private String deptName;

    /**
     * 组织编号
     */
    @TableField("DEPT_CODE")
    private String deptCode;

    /**
     * 组织类型
     */
    @TableField("DEPT_TYPE")
    private Integer deptType;

    /**
     * 父组织id
     */
    @TableField("PARENT_ID")
    private String parentId;
    /**
     * 父组织所在上级路径 数组字符串，用作回显使用
     */
    @TableField("PARENT_PATH")
    private String parentPath;
    /**
     * 组织信息描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除状态
     */
    @TableField(value ="DELETED", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

    /**
     * 序号、排序
     */
    @TableField("IDX")
    private Integer idx;


    public static final String ID = "ID";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String DEPT_TYPE = "DEPT_TYPE";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String DELETED = "DELETED";

    public static final String IDX = "IDX";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
