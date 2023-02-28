package com.lg.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.common.utils.PageUtil;
import com.lg.sys.mapper.UserMapper;
import com.lg.sys.model.converter.UserConverter;
import com.lg.sys.model.entity.DeptUser;
import com.lg.sys.model.entity.User;
import com.lg.sys.model.param.user.UserAddParam;
import com.lg.sys.model.param.user.UserEditParam;
import com.lg.sys.model.param.user.UserPageParam;
import com.lg.sys.model.vo.user.UserVo;
import com.lg.sys.service.DeptUserService;
import com.lg.sys.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private DeptUserService deptUserService;


    @Override
    public List<User> getList() {
        return list();
    }

    @Override
    public Page<User> getPage(UserPageParam userPageParam) {
        return userMapper.findByPage(PageUtil.defaultPage(userPageParam.getSize(), userPageParam.getCurrent()), userPageParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(UserEditParam userEditParam) {
        User user = UserConverter.INSTANCE.userEditParam2User(userEditParam);
        boolean update = updateById(user);
        if (update) {
            //绑定组织用户关联表
            deptUserService.updateById(DeptUser.builder().id(userEditParam.getDeptUserId()).userId(user.getId()).deptId(userEditParam.getDeptId()).build());
        }
        return update;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(UserAddParam userAddParam) {
        User user = UserConverter.INSTANCE.userAddParam2User(userAddParam);
        boolean save = save(user);
        if (save) {
            //绑定组织用户关联表
            deptUserService.save(DeptUser.builder().userId(user.getId()).deptId(userAddParam.getDeptId()).build());
        }
        return save;
    }
    @Override
    public UserVo getInfoById(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","avatar");
        queryWrapper.eq("id",id);
        User one = getOne(queryWrapper);

        return UserConverter.INSTANCE.user2UserVo(one);
    }

}
