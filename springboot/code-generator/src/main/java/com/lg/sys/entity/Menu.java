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
 * 菜单表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 菜单名
     */
    @TableField("MENU_NAME")
    private String menuName;

    /**
     * 别名
     */
    @TableField("ALIAS")
    private String alias;

    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 父菜单id
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 菜单类型（1：目录、2：菜单、3：外链）
     */
    @TableField("MENU_TYPE")
    private Integer menuType;

    /**
     * 路由
     */
    @TableField("ROUTE")
    private String route;

    /**
     * 组件
     */
    @TableField("MODULE")
    private String module;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 排序
     */
    @TableField("IDX")
    private Long idx;


    public static final String ID = "ID";

    public static final String MENU_NAME = "MENU_NAME";

    public static final String ALIAS = "ALIAS";

    public static final String ICON = "ICON";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String MENU_TYPE = "MENU_TYPE";

    public static final String ROUTE = "ROUTE";

    public static final String MODULE = "MODULE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String IDX = "IDX";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
