package com.lg.sys.mapper;

import com.lg.sys.model.entity.DeptUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@Mapper
public interface DeptUserMapper extends BaseMapper<DeptUser> {

    String  findDeptUserByUserId(String userId);

    /**
     * 删除用户与所属组织绑定关系
     * @param userId
     * @return
     */
    int deleteByUserId(String userId);
}
