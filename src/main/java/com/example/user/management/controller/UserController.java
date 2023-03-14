package com.example.user.management.controller;

import com.example.user.management.model.request.AddUserRequest;
import com.example.user.management.model.request.PageUserFilter;
import com.example.user.management.model.request.UpdateUserRequest;
import com.example.user.management.model.response.UserResponse;
import com.example.user.management.service.UserService;
import com.example.user.management.util.DataResult;
import com.example.user.management.util.PageRequest;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新建用户")
    @PostMapping("/save")
    public DataResult<Void> save(@RequestBody@Valid AddUserRequest request) {
        userService.save(request);
        return DataResult.success();
    }

    @ApiOperation(value = "单个删除用户")
    @DeleteMapping("/{id}/delete")
    public DataResult<Void> deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
        return DataResult.success();
    }

    @ApiOperation(value = "批量删除用户")
    @PostMapping("/batch/delete")
    public DataResult<Void> deleteById(@RequestBody List<Long> ids) {
        userService.delete(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "编辑用户信息")
    @PostMapping("/{id}/update")
    public DataResult<Void> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequest request) {
        userService.update(id, request);
        return DataResult.success();
    }

    @ApiOperation(value = "根据id查询用户信息")
    @GetMapping("/{id}/detail")
    public DataResult<UserResponse> getInfoById(@PathVariable("id")  Long id){
        return DataResult.success(userService.getInfoById(id));
    }

    @ApiOperation(value = "搜索用户列表")
    @PostMapping("/page")
    public DataResult<Page<UserResponse>> page(@RequestBody PageRequest<PageUserFilter> request) {
        return DataResult.success(userService.page(request));
    }
}
