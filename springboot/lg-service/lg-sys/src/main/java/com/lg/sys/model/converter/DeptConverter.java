package com.lg.sys.model.converter;

import com.lg.sys.model.dto.dept.DeptAddDTO;
import com.lg.sys.model.dto.dept.DeptEditDTO;
import com.lg.sys.model.entity.Dept;
import org.mapstruct.Mapper;
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

    Dept deptAddDTO2Dept(DeptAddDTO deptAddDto);


    Dept deptEditDTO2Dept(DeptEditDTO deptEditDto);
}
