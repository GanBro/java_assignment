package com.ganbro.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户DTO", description = "用户数据传输对象")
public class UserDto {
    private String username;
    private String password;
    private Integer isAdmin;
}
