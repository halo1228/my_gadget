package com.lg.sys.entity;

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
 * 部门-用户绑定表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
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

    @TableField("BIND_TIME")
    private Date bindTime;

    /**
     * 绑定类型，1：属于，2：分管
     */
    @TableField("BIND_TYPE")
    private Integer bindType;


    public static final String ID = "ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String USER_ID = "USER_ID";

    public static final String BIND_TIME = "BIND_TIME";

    public static final String BIND_TYPE = "BIND_TYPE";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
