package com.lg.controller.sys;

import cn.hutool.core.lang.tree.Tree;
import com.lg.common.pojo.ApiResult;
import com.lg.sys.model.dto.dept.DeptAddDTO;
import com.lg.sys.model.dto.dept.DeptEditDTO;
import com.lg.sys.model.entity.Dept;
import com.lg.sys.model.dto.dept.DeptPageDTO;
import com.lg.sys.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 组织管理 前端控制器
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "组织管理相关接口")
public class DeptController {

    @Resource
    DeptService deptService;


    @PostMapping("page")
    @ApiOperation("获取用户列表-分页")
    public ApiResult<?> getPage(@RequestBody DeptPageDTO params) {
        return ApiResult.data(deptService.getPage(params));
    }

    @PostMapping("add")
    @ApiOperation("添加组织")
    public ApiResult<?> add(@RequestBody @Valid DeptAddDTO deptAddDto) {
        boolean save = deptService.add(deptAddDto);
        return save ? ApiResult.ok() : ApiResult.error();
    }

    @PostMapping("update")
    @ApiOperation("添加组织")
    public ApiResult<?> update(@RequestBody @Valid DeptEditDTO deptEditDto) {
        boolean save = deptService.updateById(deptEditDto);
        return save ? ApiResult.ok() : ApiResult.error();
    }

    @GetMapping("detail")
    @ApiOperation("根据组织ID获取编辑组织明细信息")
    public ApiResult<?> detail(@RequestParam String id) {
        Dept dept = deptService.getById(id);
        return ApiResult.data(dept);
    }

    @GetMapping("lazyTree")
    @ApiOperation("组织树查询（懒加载）")
    public ApiResult<?> lazyTree(@RequestParam @Valid String pId) {
        List<Tree<String>> list = deptService.lazyTree(pId);
        return ApiResult.data(list);
    }

    @GetMapping("tree")
    @ApiOperation("组织树查询")
    public ApiResult<?> tree() {
        List<Tree<String>> list = deptService.tree();
        return ApiResult.data(list);
    }
}
