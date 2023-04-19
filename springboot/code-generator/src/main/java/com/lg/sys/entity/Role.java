package com.lg.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    private String roleName;

    /**
     * 父级角色id
     */
    @TableField("PARENT_ROLE_ID")
    private String parentRoleId;

    /**
     * 角色类型（1：人员角色、2：组织角色）
     */
    @TableField("ROLE_TYPE")
    private Integer roleType;

    /**
     * 所属模块
     */
    @TableField("MODULE")
    private Integer module;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 删除状态
     */
    @TableField("DELETED")
    @TableLogic
    private Integer deleted;


    public static final String ID = "ID";

    public static final String ROLE_NAME = "ROLE_NAME";

    public static final String PARENT_ROLE_ID = "PARENT_ROLE_ID";

    public static final String ROLE_TYPE = "ROLE_TYPE";

    public static final String MODULE = "MODULE";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String DELETED = "DELETED";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
