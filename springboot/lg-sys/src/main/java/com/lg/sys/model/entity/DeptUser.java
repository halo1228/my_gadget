package com.lg.sys.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
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
@Builder
@Accessors(chain = true)
@TableName("sys_dept_user")
public class DeptUser extends Model<DeptUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    @TableField("DEPT_ID")
    private String deptId;

    @TableField("USER_ID")
    private String userId;

    @TableField(value = "BIND_TIME", fill = FieldFill.INSERT)
    private Date bindTime;


    public static final String ID = "ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String USER_ID = "USER_ID";

    public static final String BIND_TIME = "BIND_TIME";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
