package com.ganbro.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "逾期返回对象", description = "方便返回值")
public class OverdueDto {
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "标记借书是否成功")
    private boolean flag;
}
