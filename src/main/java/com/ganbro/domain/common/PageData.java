package com.ganbro.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页数据对象", description = "包含总记录数、每页显示数、当前页码和数据列表")
public class PageData<T> {
    @ApiModelProperty(value = "总记录数")
    private long totalItems;
    @ApiModelProperty(value = "每页显示的记录数")
    private int pageSize;
    @ApiModelProperty(value = "当前页码")
    private int currentPage;
    @ApiModelProperty(value = "数据列表")
    private List<T> list;
}
