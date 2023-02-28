package com.lg.sys.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.common.utils.PageUtil;
import com.lg.sys.model.converter.DeptConverter;
import com.lg.sys.model.entity.Dept;
import com.lg.sys.mapper.DeptMapper;
import com.lg.sys.model.param.dept.DeptAddParam;
import com.lg.sys.model.param.dept.DeptEditParam;
import com.lg.sys.model.param.dept.DeptPageParam;
import com.lg.sys.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public Page<Dept> getPage(DeptPageParam deptPageParam) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .likeRight(StringUtils.isNotBlank(deptPageParam.getDeptName()), Dept::getDeptName, deptPageParam.getDeptName())
                .eq(StringUtils.isNotBlank(deptPageParam.getParentId()), Dept::getParentId, deptPageParam.getParentId())/*
                .or()
                .eq(StringUtils.isNotBlank(deptPageParam.getParentId()), Dept::getId, deptPageParam.getParentId())*/;
        return page(PageUtil.defaultPage(deptPageParam.getSize(), deptPageParam.getCurrent()), queryWrapper);
    }

    @Override
    public boolean add(DeptAddParam deptAddParam) {
        Dept dept = DeptConverter.INSTANCE.deptAddParam2Dept(deptAddParam);
        if (StringUtils.isBlank(dept.getParentId())) {
            //默认顶级组织id为0
            dept.setParentId("0");
        }
        return save(dept);
    }

    @Override
    public boolean updateById(DeptEditParam deptEditParam) {
        Dept dept = DeptConverter.INSTANCE.deptEditParam2Dept(deptEditParam);
        return updateById(dept);
    }

    @Override
    public List<Tree<String>> lazyTree(String pId) {
        if (StringUtils.isBlank(pId)) {
            pId = "root";
        }
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dept::getParentId, pId).orderByAsc(Dept::getIdx);
        ;
        List<Dept> list = list(queryWrapper);
        List<TreeNode<String>> treeNodeList = list.stream().map(dept ->
                        new TreeNode<>(dept.getId(), dept.getParentId(),
                                dept.getDeptName(), dept.getIdx()))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, pId);
    }

    @Override
    public List<Tree<String>> tree() {
        LambdaQueryWrapper<Dept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Dept::getIdx);
        List<Dept> deptList = this.list(lambdaQueryWrapper);
        List<TreeNode<String>> treeNodeList = deptList.stream().map(dept ->
                        new TreeNode<>(dept.getId(), dept.getParentId(),
                                dept.getDeptName(), dept.getIdx()))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");
    }
}
