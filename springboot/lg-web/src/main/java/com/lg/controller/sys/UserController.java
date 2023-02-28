package com.lg.controller.sys;

import com.lg.common.pojo.ApiResult;
import com.lg.sys.model.entity.User;
import com.lg.sys.model.param.user.UserAddParam;
import com.lg.sys.model.param.user.UserEditParam;
import com.lg.sys.model.param.user.UserPageParam;
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


    @GetMapping("list")
    @ApiOperation("获取用户列表")
    public ApiResult<?> getList() {
        return ApiResult.data(userService.getList());
    }


    @PostMapping("page")
    @ApiOperation("获取用户列表-分页")
    public ApiResult<?> getPage(@RequestBody @Valid UserPageParam params) {
        return ApiResult.data(userService.getPage(params));
    }

    @PostMapping("add")
    @ApiOperation("添加用户")
    public ApiResult<?> add(@RequestBody @Valid UserAddParam userAddParam) {
        boolean save = userService.add(userAddParam);

        return save ? ApiResult.ok() : ApiResult.error();
    }

    @PostMapping("update")
    @ApiOperation("根据用户ID编辑用户")
    public ApiResult<?> update(@RequestBody @Valid UserEditParam userEditParam) {
        boolean update = userService.updateById(userEditParam);
        return update ? ApiResult.ok() : ApiResult.error();
    }

    @GetMapping("detail")
    @ApiOperation("根据用户ID获取编辑用户明细信息")
    public ApiResult<?> detail(@RequestParam String id) {
        User user = userService.getById(id);
        return ApiResult.data(user);
    }

    @GetMapping("delete")
    @ApiOperation("根据用户ID删除用户信息")
    public ApiResult<?> delete(@RequestParam String id) {
        boolean delete = userService.removeById(id,true);
        return delete ? ApiResult.ok() : ApiResult.error();
    }

}
