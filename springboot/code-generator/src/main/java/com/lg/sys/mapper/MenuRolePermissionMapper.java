package com.lg.sys.mapper;

import com.lg.sys.entity.MenuRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单-角色（权限）绑定表 Mapper 接口
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Mapper
public interface MenuRolePermissionMapper extends BaseMapper<MenuRolePermission> {

}
