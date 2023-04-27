package com.lg.sys.service;

import com.lg.sys.model.entity.DeptUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
public interface DeptUserService extends IService<DeptUser> {

    boolean updateBind(DeptUser deptUser);

}
