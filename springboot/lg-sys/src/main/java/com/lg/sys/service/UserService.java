package com.lg.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.sys.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.sys.model.param.user.UserAddParam;
import com.lg.sys.model.param.user.UserEditParam;
import com.lg.sys.model.param.user.UserPageParam;
import com.lg.sys.model.vo.user.UserVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
public interface UserService extends IService<User> {

    UserVo getInfoById(String id);

    /**
     * 获取用户list 不分页
     *
     * @return
     */
    List<User> getList();

    /**
     * 获取分页
     *
     * @param userPageParam
     * @return
     */
    Page<User> getPage(UserPageParam userPageParam);

    /**
     * 根据id 修改信息
     *
     * @param userEditParam
     * @return
     */
    boolean updateById(UserEditParam userEditParam);

    /**
     * 添加一条数据
     *
     * @param userAddParam
     * @return
     */
    boolean add(UserAddParam userAddParam);

}
