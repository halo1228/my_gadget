package com.lg.sys.service.impl;

import com.lg.sys.model.entity.DeptUser;
import com.lg.sys.mapper.DeptUserMapper;
import com.lg.sys.service.DeptUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@Service
public class DeptUserServiceImpl extends ServiceImpl<DeptUserMapper, DeptUser> implements DeptUserService {
    @Resource
    DeptUserMapper deptUserMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBind(DeptUser deptUser) {
        boolean update = false;
        if (StringUtils.isNotBlank(deptUser.getId())) {
            //根据id修改
            update = updateById(deptUser);
        } else {
            //根据用户id修改 每个用户只存在一个所属组织
            int i = deptUserMapper.deleteByUserId(deptUser.getUserId());
            if (i > 0) {
                update = save(deptUser);
            }
        }
        return update;
    }
}
