package com.ganbro.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "修改书返回对象")
public class EditBookDetailDto {
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "标记修改是否成功")
    private Boolean flag;
}
