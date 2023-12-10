package com.ganbro.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "删除用户返回对象")
public class DeleteUserInfoDto {
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "标记删除是否成功")
    private Boolean flag;
}
