package com.ganbro.controller;

import com.ganbro.domain.common.Result;
import com.ganbro.domain.dto.UserDto;
import com.ganbro.domain.entity.User;
import com.ganbro.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@Api(tags = "登录接口")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result<String> login(@RequestBody User user) {
        // 获取用户名和密码
        String username = user.getUsername();
        String password = loginService.findPasswordByUsername(username);
        if (password == null) {
            return Result.error(null, "登录失败，用户名或密码错误");
        }
        if (user.getPassword().equals(password)) {
            return Result.success("登录成功");
        }
        return Result.error(null, "登录失败，用户名或密码错误");
    }

    @PostMapping("/adminlogin")
    @ApiOperation(value = "管理员登录")
    public Result<String> adminLogin(@RequestBody User user) {
        // 获取用户名和密码
        String username = user.getUsername();

        String password = loginService.findPasswordByUsername(username);
        int flag = loginService.findIsAdminByUsername(username);
        if (password == null) {
            return Result.error(null, "登录失败，用户名或密码错误");
        }
        if (user.getPassword().equals(password)) {
            if (flag == 0) {
                return Result.error("没有管理员权限!!!");
            }
            return Result.success("登录成功");
        }
        return Result.error(null, "登录失败，用户名或密码错误");

    }
}
