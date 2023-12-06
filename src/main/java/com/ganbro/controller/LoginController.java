package com.ganbro.controller;

import com.ganbro.domain.common.Result;
import com.ganbro.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@Api(tags = "登录接口")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @ApiOperation(value = "登录")
    public Result<String> login(@RequestBody Map<String, String> loginRequest) {
        // 获取用户名和密码
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        boolean flag = loginService.findByUsernameAndPassword(username, password);

        if (flag) {
            return Result.success("登录成功");
        } else {
            return Result.error(401, "登录失败，用户名或密码错误");
        }

    }
}
