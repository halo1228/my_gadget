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
 * 
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_router")
public class Router extends Model<Router> {

    private static final long serialVersionUID = 1L;

    /**
     * 路由唯一ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 路由中文显示
     */
    @TableField("LABEL")
    private String label;

    /**
     * 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
     */
    @TableField("NAME")
    private String name;

    /**
     *  一级路由路径
     */
    @TableField("PATH")
    private String path;

    /**
     * 包含组件 默认 Layout 以 # 代替
     */
    @TableField("COMPONENT")
    private String component;

    /**
     * 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击	重定向地址，在面包屑中点击会重定向去的地址
     */
    @TableField("REDIRECT")
    private String redirect;

    /**
     * 不在侧边栏显示 实际就是路由隐藏
     */
    @TableField("HIDDEN")
    private Integer hidden;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
    	只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
     	若你想不管路由下面的 children 声明的个数都显示你的根路由
     	你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
     */
    @TableField("ALWAYS_SHOW")
    private Integer alwaysShow;

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    @TableField("TITLE")
    private String title;

    /**
     * icon
     */
    @TableField("ICON")
    private String icon;

    /**
     * 如果设置为true，则会被 <keep-alive> 缓存(默认 false)
     */
    @TableField("KEEP_ALIVE")
    private Integer keepAlive;

    /**
     * 如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)
     */
    @TableField("BREADCRUMB")
    private Integer breadcrumb;

    /**
     * 如果设置为true，它则会固定在tags-view中(默认 false)
     */
    @TableField("AFFIX")
    private Integer affix;

    /**
     * 当路由设置了该属性，则会高亮相对应的侧边栏。
          这在某些场景非常有用，比如：一个文章的列表页路由为：/article/list
         	 点击文章进入文章详情页，这时候路由为/article/1，但你想在侧边栏高亮文章列表的路由，就可以进行如下设置
     */
    @TableField("ACTIVE_MENU")
    private String activeMenu;

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
     * 排序
     */
    @TableField("IDX")
    private Integer idx;


    public static final String ID = "ID";

    public static final String LABEL = "LABEL";

    public static final String NAME = "NAME";

    public static final String PATH = "PATH";

    public static final String COMPONENT = "COMPONENT";

    public static final String REDIRECT = "REDIRECT";

    public static final String HIDDEN = "HIDDEN";

    public static final String ALWAYS_SHOW = "ALWAYS_SHOW";

    public static final String TITLE = "TITLE";

    public static final String ICON = "ICON";

    public static final String KEEP_ALIVE = "KEEP_ALIVE";

    public static final String BREADCRUMB = "BREADCRUMB";

    public static final String AFFIX = "AFFIX";

    public static final String ACTIVE_MENU = "ACTIVE_MENU";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String MENU_TYPE = "MENU_TYPE";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String IDX = "IDX";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
