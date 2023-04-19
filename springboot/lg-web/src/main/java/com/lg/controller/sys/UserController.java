package com.lg.controller.sys;

import com.lg.common.pojo.ApiResult;
import com.lg.sys.model.dto.user.UserAddDTO;
import com.lg.sys.model.dto.user.UserEditDTO;
import com.lg.sys.model.dto.user.UserPageDTO;
import com.lg.sys.model.vo.user.UserDetailVO;
import com.lg.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author liuga
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户管理相关接口")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("page")
    @ApiOperation("获取用户列表-分页")
    public ApiResult<?> getPage(@RequestBody @Valid UserPageDTO params) {
        return ApiResult.data(userService.getPage(params));
    }

    @PostMapping("add")
    @ApiOperation("添加用户")
    public ApiResult<?> add(@RequestBody @Valid UserAddDTO userAddDto) {
        boolean save = userService.add(userAddDto);
        return save ? ApiResult.ok() : ApiResult.error();
    }

    @PostMapping("update")
    @ApiOperation("根据用户ID编辑用户")
    public ApiResult<?> update(@RequestBody @Valid UserEditDTO userEditDto) {
        boolean update = userService.updateById(userEditDto);
        return update ? ApiResult.ok() : ApiResult.error();
    }

    @GetMapping("detail")
    @ApiOperation("根据用户ID获取编辑用户明细信息")
    public ApiResult<?> detail(@RequestParam String id) {
        UserDetailVO user = userService.editInfo(id);
        return ApiResult.data(user);
    }

    @GetMapping("delete")
    @ApiOperation("根据用户ID删除（逻辑删除）用户信息（支持批量删除，ID用,隔开）")
    public ApiResult<?> delete(@RequestParam String ids) {
        boolean delete = userService.remove(ids, false);
        return delete ? ApiResult.ok() : ApiResult.error();
    }

    @GetMapping("clear")
    @ApiOperation("根据用户ID彻底清除（物理删除）用户信息（支持批量删除，ID用,隔开）")
    public ApiResult<?> clear(@RequestParam String ids) {
        boolean delete = userService.remove(ids, true);
        return delete ? ApiResult.ok() : ApiResult.error();
    }



}
