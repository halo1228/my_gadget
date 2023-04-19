package com.lg.sys.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lg.common.utils.PageUtil;
import com.lg.sys.model.converter.DeptConverter;
import com.lg.sys.model.dto.dept.DeptAddDTO;
import com.lg.sys.model.dto.dept.DeptEditDTO;
import com.lg.sys.model.entity.Dept;
import com.lg.sys.mapper.DeptMapper;
import com.lg.sys.model.dto.dept.DeptPageDTO;
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
    public Page<Dept> getPage(DeptPageDTO deptPageDto) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .likeRight(StringUtils.isNotBlank(deptPageDto.getDeptName()), Dept::getDeptName, deptPageDto.getDeptName())
                .eq(StringUtils.isNotBlank(deptPageDto.getParentId()), Dept::getParentId, deptPageDto.getParentId())
                .or()
                .eq(StringUtils.isNotBlank(deptPageDto.getParentId()), Dept::getId, deptPageDto.getParentId());
        return page(PageUtil.defaultPage(deptPageDto.getSize(), deptPageDto.getCurrent()), queryWrapper);
    }

    @Override
    public boolean add(DeptAddDTO deptAddDto) {
        Dept dept = DeptConverter.INSTANCE.deptAddDTO2Dept(deptAddDto);
        if (StringUtils.isBlank(dept.getParentId())) {
            //默认顶级组织id为0
            dept.setParentId("0");
        }
        return save(dept);
    }

    @Override
    public boolean updateById(DeptEditDTO deptEditDto) {
        Dept dept = DeptConverter.INSTANCE.deptEditDTO2Dept(deptEditDto);
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
