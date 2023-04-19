package com.lg.sys.mapper;

import com.lg.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
