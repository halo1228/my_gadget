package com.lg.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.auth.model.entity.Auth;
import com.lg.auth.model.vo.AuthVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuga
 * @since 2023-02-24 11:37
 * Description:
 */

@Mapper
public interface AuthMapper extends BaseMapper<Auth> {
    /**
     * 获取用户账号信息
     * @param id
     * @return
     */
    AuthVo getUserInfo(String id);

    /*
     * 2，获取用户角色、权限数据
     * 3、获取用户菜单数据 动态路由
     */
}
