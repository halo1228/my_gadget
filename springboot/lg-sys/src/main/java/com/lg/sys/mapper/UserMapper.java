package com.lg.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.sys.model.entity.User;
import com.lg.sys.model.param.user.UserPageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    Page<User> findByPage(Page<User> page, @Param("user") UserPageParam user);

}
