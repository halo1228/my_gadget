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
 * 菜单-角色（权限）绑定表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_menu_role_permission")
public class MenuRolePermission extends Model<MenuRolePermission> {

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

    /**
     * 菜单id
     */
    @TableField("MENU_ID")
    private String menuId;

    /**
     * 绑定类型（1：菜单-角色、2：菜单-权限）
     */
    @TableField("BIND_TYPE")
    private Integer bindType;


    public static final String ID = "ID";

    public static final String ROLE_ID = "ROLE_ID";

    public static final String PERMISSION_ID = "PERMISSION_ID";

    public static final String MENU_ID = "MENU_ID";

    public static final String BIND_TYPE = "BIND_TYPE";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
