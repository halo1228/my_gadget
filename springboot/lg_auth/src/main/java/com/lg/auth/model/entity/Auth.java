package com.lg.auth.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

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
//@Accessors(chain = true)
@TableName("sys_user")
public class Auth extends Model<Auth> {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private String id;


    /**
     * 状态 0:正常
     */
    @TableField(value ="STATUS", fill = FieldFill.INSERT)
    private Integer status;



    /**
     * 登录账号
     */
    @TableField("ACCESS")
    private String access;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

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
     * 删除状态
     */
    @TableField(value ="DELETED", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;
    @Override
    public Serializable pkVal() {
        return this.id;
    }

}

