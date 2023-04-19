package com.lg.sys.service.impl;

import com.lg.sys.entity.RoleUser;
import com.lg.sys.mapper.RoleUserMapper;
import com.lg.sys.service.RoleUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-用户绑定表 服务实现类
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

}
