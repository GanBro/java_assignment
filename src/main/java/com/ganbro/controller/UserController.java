package com.ganbro.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.Result;
import com.ganbro.domain.common.ReturnModel;
import com.ganbro.domain.entity.UserInfo;
import com.ganbro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Api(tags = "用户管理接口")
public class UserController {

    private final UserService userService;

    @GetMapping("/searchByPage")
    @ApiOperation(value = "分页搜索用户信息")
    public Result<PageData<UserInfo>> searchUsersByPage(@RequestParam("query") String query,
                                                        @RequestParam("pageNum") int pageNum,
                                                        @RequestParam("pageSize") int pageSize) {
        PageData<UserInfo> pageData = userService.searchUserInfoByPage(query, pageNum, pageSize);
        return Result.success(pageData);
    }

    @GetMapping("/{username}")
    @ApiOperation(value = "查询用户信息")
    public Result<UserInfo> findUserInfo(@PathVariable String username) {
        UserInfo userInfo = userService.selectUserInfo(username);
        if (!BeanUtil.isEmpty(userInfo)) {
            return Result.success(userInfo, "查询成功!");
        } else {
            return Result.success(userInfo, "用户信息为空!");
        }
    }

    @PostMapping()
    @ApiOperation(value = "添加借阅人员信息")
    public Result<Void> addUserInfo(@RequestBody UserInfo userInfo) {
        ReturnModel returnModel = userService.addUserInfo(userInfo);
        if (returnModel.getFlag()) {
            return Result.success(null, returnModel.getMessage());
        } else {
            return Result.error(null, returnModel.getMessage());
        }

    }

    @PutMapping()
    @ApiOperation(value = "修改借阅人员信息")
    public Result<Void> updateUserInfo(@RequestBody UserInfo userInfo) {
        ReturnModel returnModel = userService.updateUserInfo(userInfo);
        if (returnModel.getFlag()) {
            return Result.success(null, returnModel.getMessage());
        } else {
            return Result.error(null, returnModel.getMessage());
        }

    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "删除借阅人员信息")
    public Result<Void> deleteBorrower(@PathVariable Integer userId) {
        ReturnModel returnModel = userService.deleteUserById(userId);
        if (returnModel.getFlag()) {
            return Result.success(null, returnModel.getMessage());
        } else {
            return Result.error(null, returnModel.getMessage());
        }

    }

    @PutMapping("/updateDueBooks")
    @ApiOperation(value = "更新逾期图书")
    public Result<Void> updateDueBooks(@PathParam("username") String username) {
        userService.updateDueBooks(username);
        return Result.success(null, "更新成功!");
    }

    @PutMapping("/resetDueBooks")
    @ApiOperation(value = "重置逾期图书")
    public Result<Void> resetDueBooks(@PathParam("username") String username) {
        userService.resetDueBooks(username);
        return Result.success(null, "重置成功!");
    }
}
