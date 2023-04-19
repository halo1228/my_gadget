package com.lg.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色-部门绑定表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role_dept")
public class RoleDept extends Model<RoleDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 组织id
     */
    @TableField("DEPT_ID")
    private String deptId;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private String roleId;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;


    public static final String ID = "ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String ROLE_ID = "ROLE_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
