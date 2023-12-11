package com.ganbro.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "统一返回格式")
public class ReturnModel {
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "标记执行是否成功")
    private Boolean flag;
}
