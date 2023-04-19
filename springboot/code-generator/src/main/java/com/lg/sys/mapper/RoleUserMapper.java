package com.lg.sys.mapper;

import com.lg.sys.entity.RoleUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色-用户绑定表 Mapper 接口
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {

}
