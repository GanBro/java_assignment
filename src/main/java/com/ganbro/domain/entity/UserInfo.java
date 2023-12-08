package com.ganbro.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户信息", description = "用户信息实体类")
public class UserInfo {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "最大可借图书数量")
    private Integer maxBooksAllowed;

    @ApiModelProperty(value = "已借图书数量")
    private Integer borrowedBooks;

    @ApiModelProperty(value = "是否为VIP用户")
    private Boolean isVip;

    @ApiModelProperty(value = "逾期图书数量")
    private Integer overdueBooks;
}
