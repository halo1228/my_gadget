package com.lg.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色-权限绑定表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role_permission")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private String roleId;

    /**
     * 权限id
     */
    @TableField("PERMISSION_ID")
    private String permissionId;


    public static final String ID = "ID";

    public static final String ROLE_ID = "ROLE_ID";

    public static final String PERMISSION_ID = "PERMISSION_ID";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
