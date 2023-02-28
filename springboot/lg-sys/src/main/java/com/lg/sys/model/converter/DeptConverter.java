package com.lg.sys.model.converter;

import com.lg.sys.model.entity.Dept;
import com.lg.sys.model.entity.User;
import com.lg.sys.model.param.dept.DeptAddParam;
import com.lg.sys.model.param.dept.DeptEditParam;
import com.lg.sys.model.param.user.UserAddParam;
import com.lg.sys.model.param.user.UserEditParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author liuga
 * @since 2023-02-15 14:35
 * Description:
 * 用户模块 bean 相关转换器
 */
@Mapper
public interface DeptConverter {

    DeptConverter INSTANCE = Mappers.getMapper(DeptConverter.class);

    Dept deptAddParam2Dept(DeptAddParam deptAddParam);


    Dept deptEditParam2Dept(DeptEditParam deptEditParam);
}
