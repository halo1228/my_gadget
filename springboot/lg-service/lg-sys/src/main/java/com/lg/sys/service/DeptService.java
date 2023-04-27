package com.lg.sys.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.sys.model.dto.dept.DeptEditDTO;
import com.lg.sys.model.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lg.sys.model.dto.dept.DeptAddDTO;
import com.lg.sys.model.dto.dept.DeptPageDTO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
public interface DeptService extends IService<Dept> {

    /**
     * 获取分页
     *
     * @param deptPageDto
     * @return
     */
    Page<Dept> getPage(DeptPageDTO deptPageDto);

    /**
     * 添加
     *
     * @param deptAddDto
     * @return
     */
    boolean add(DeptAddDTO deptAddDto);


    boolean updateById(DeptEditDTO deptEditDto);
    /**
     * 树查询 懒加载
     *
     * @param pId 父Id
     * @return
     */
    List<Tree<String>> lazyTree(String pId);

    /**
     * 树查询 数据量小 推荐
     * @return
     */
    List<Tree<String>> tree();
}
