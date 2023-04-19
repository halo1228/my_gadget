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
 * 权限表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 权限名
     */
    @TableField("PERMISSION_NAME")
    private String permissionName;

    /**
     * 权限类型
     */
    @TableField("PERMISSION_TYPE")
    private Integer permissionType;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 所属权限模块
     */
    @TableField("MODULE")
    private String module;

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
    private String deleted;


    public static final String ID = "ID";

    public static final String PERMISSION_NAME = "PERMISSION_NAME";

    public static final String PERMISSION_TYPE = "PERMISSION_TYPE";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String MODULE = "MODULE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String DELETED = "DELETED";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
