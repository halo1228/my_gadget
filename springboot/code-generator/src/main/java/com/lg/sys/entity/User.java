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
 * 用户信息表
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;

    /**
     * 员工编号
     */
    @TableField("USER_CODE")
    private String userCode;

    /**
     * 性别
     */
    @TableField("SEX")
    private Integer sex;

    /**
     * 手机号
     */
    @TableField("TEL")
    private String tel;

    /**
     * 生日
     */
    @TableField("BIRTHDAY")
    private Date birthday;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 地址
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 头像
     */
    @TableField("AVATAR")
    private String avatar;

    /**
     * 个人简介
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 状态
     */
    @TableField("STATUS")
    private Integer status;

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
    @TableField("DELETED")
    @TableLogic
    private Integer deleted;

    /**
     * 序号、排序
     */
    @TableField("IDX")
    private Integer idx;

    /**
     * 登录账号
     */
    @TableField("ACCESS")
    private String access;

    /**
     * 密码 理论上128位即可
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 账号类型（1：手机号、2：邮箱、3：自拟）
     */
    @TableField("ACCESS_TYPE")
    private Integer accessType;

    /**
     * 锁定状态（1：正常、2：锁定、3：禁用）
     */
    @TableField("IS_LOCK")
    private Integer isLock;

    /**
     * 锁定时间
     */
    @TableField("LOCK_TIME")
    private Date lockTime;

    /**
     * 上次登陆时间
     */
    @TableField("LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 登陆次数
     */
    @TableField("LOGIN_COUNT")
    private Long loginCount;


    public static final String ID = "ID";

    public static final String NAME = "NAME";

    public static final String USER_CODE = "USER_CODE";

    public static final String SEX = "SEX";

    public static final String TEL = "TEL";

    public static final String BIRTHDAY = "BIRTHDAY";

    public static final String EMAIL = "EMAIL";

    public static final String ADDRESS = "ADDRESS";

    public static final String AVATAR = "AVATAR";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String STATUS = "STATUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String DELETED = "DELETED";

    public static final String IDX = "IDX";

    public static final String ACCESS = "ACCESS";

    public static final String PASSWORD = "PASSWORD";

    public static final String ACCESS_TYPE = "ACCESS_TYPE";

    public static final String IS_LOCK = "IS_LOCK";

    public static final String LOCK_TIME = "LOCK_TIME";

    public static final String LAST_LOGIN_TIME = "LAST_LOGIN_TIME";

    public static final String LOGIN_COUNT = "LOGIN_COUNT";

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
