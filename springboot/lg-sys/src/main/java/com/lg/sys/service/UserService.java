package com.lg.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.sys.model.dto.user.UserEditDTO;
import com.lg.sys.model.dto.user.UserPassDTO;
import com.lg.sys.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.sys.model.dto.user.UserAddDTO;
import com.lg.sys.model.dto.user.UserPageDTO;
import com.lg.sys.model.vo.user.UserDetailVO;
import com.lg.sys.model.vo.user.UserVO;

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


    /**
     * 获取分页
     *
     * @param userPageDto
     * @return
     */
    Page<UserVO> getPage(UserPageDTO userPageDto);

    /**
     * 根据id 修改信息
     *
     * @param userEditDto
     * @return
     */
    boolean updateById(UserEditDTO userEditDto);

    /**
     * 添加一条数据
     *
     * @param userAddDto
     * @return
     */
    boolean add(UserAddDTO userAddDto);

    /**
     * 根据id 获取编辑信息
     *
     * @param id
     * @return
     */
    UserDetailVO editInfo(String id);

    /**
     * 删除
     *
     * @param ids   id
     * @param isDel 是否彻底删除
     * @return
     */
    boolean remove(String ids, boolean isDel);

    /**
     * 修改密码
     *
     * @param userPassDTO
     * @return
     */
    boolean changePassword(UserPassDTO userPassDTO);

}
