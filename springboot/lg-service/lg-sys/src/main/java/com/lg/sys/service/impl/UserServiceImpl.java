package com.lg.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lg.common.exception.CommonException;
import com.lg.common.utils.PageUtil;
import com.lg.common.utils.SmCryptoUtil;
import com.lg.sys.enums.UserExceptionEnum;
import com.lg.sys.mapper.UserMapper;
import com.lg.sys.model.converter.UserConverter;
import com.lg.sys.model.dto.user.UserAddDTO;
import com.lg.sys.model.dto.user.UserEditDTO;
import com.lg.sys.model.dto.user.UserPageDTO;
import com.lg.sys.model.dto.user.UserPassDTO;
import com.lg.sys.model.entity.DeptUser;
import com.lg.sys.model.entity.User;
import com.lg.sys.model.vo.user.UserDetailVO;
import com.lg.sys.model.vo.user.UserVO;
import com.lg.sys.service.DeptUserService;
import com.lg.sys.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
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
    public Page<UserVO> getPage(UserPageDTO userPageDto) {
        return userMapper.findByPage(PageUtil.defaultPage(userPageDto.getSize(), userPageDto.getCurrent()), userPageDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(UserEditDTO userEditDto) {
        User user = UserConverter.INSTANCE.userEditDTO2User(userEditDto);
        boolean update = updateById(user);
        if (update) {
            //绑定组织用户关联表
            DeptUser build = DeptUser.builder().userId(user.getId()).deptId(userEditDto.getDeptId()).bindType(1).build();
            boolean b = deptUserService.updateBind(build);
            if (!b) {
                throw new CommonException();
            }
        }
        return update;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(UserAddDTO userAddDto) {
        User user = UserConverter.INSTANCE.userAddDTO2User(userAddDto);
        //先判断账号是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccess, user.getAccess());
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new CommonException(UserExceptionEnum.ACCOUNT_REPEAT.getValue());
        }
        //加解密密码
        String pwd;
        try {
            pwd = SmCryptoUtil.doHashValue(SmCryptoUtil.sm2Decrypt(userAddDto.getPassword()));
        } catch (Exception e) {
            throw new CommonException(UserExceptionEnum.PWD_DECRYPT_ERROR.getValue());
        }
        user.setPassword(pwd);
        //存储
        boolean save = save(user);
        if (save) {
            //绑定组织用户关联表
            deptUserService.save(DeptUser.builder().userId(user.getId()).deptId(userAddDto.getDeptId()).build());
        }
        return save;
    }


    public UserDetailVO editInfo(String id) {
        // 1 隶属 2 分管
        return userMapper.findEditDataById(id, 1);
    }

    @Override
    public boolean remove(String ids, boolean isDel) {
        boolean delete;
        if (ids.contains(",")) {
            String[] split = ids.split(",");
            List<String> list = Arrays.asList(split);
            delete = removeByIds(list, !isDel);
        } else {
            delete = removeById(ids, !isDel);
        }
        return delete;
    }


    @Override
    public boolean changePassword(UserPassDTO userPassDTO) {
        return false;
    }

    private boolean validatePass(String id, String newPass) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.select(User::getPassword).eq(User::getId, id);
        User one = getOne(queryWrapper);
        return StrUtil.isNotBlank(newPass) && newPass.equals(one.getPassword());
    }

}
