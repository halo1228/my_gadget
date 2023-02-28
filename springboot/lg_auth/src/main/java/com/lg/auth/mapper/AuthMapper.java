package com.lg.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lg.auth.model.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liuga
 * @since 2023-02-24 11:37
 * Description:
 */

@Mapper
public interface AuthMapper extends BaseMapper<Auth> {

}
