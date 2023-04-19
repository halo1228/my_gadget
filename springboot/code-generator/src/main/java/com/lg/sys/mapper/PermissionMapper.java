package com.lg.sys.mapper;

import com.lg.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
