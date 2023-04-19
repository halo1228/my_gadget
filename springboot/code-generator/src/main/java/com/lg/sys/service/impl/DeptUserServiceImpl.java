package com.lg.sys.service.impl;

import com.lg.sys.entity.DeptUser;
import com.lg.sys.mapper.DeptUserMapper;
import com.lg.sys.service.DeptUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门-用户绑定表 服务实现类
 * </p>
 *
 * @author liuga
 * @since 2023-04-14
 */
@Service
public class DeptUserServiceImpl extends ServiceImpl<DeptUserMapper, DeptUser> implements DeptUserService {

}
