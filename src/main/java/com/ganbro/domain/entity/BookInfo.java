package com.ganbro.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "图书信息", description = "图书信息实体类")
public class BookInfo {

    @ApiModelProperty(value = "图书信息ID")
    private Integer bookInfoId;

    @ApiModelProperty(value = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "出版社")
    private String publisher;

    @ApiModelProperty(value = "出版日期")
    private String publishDate;

    @ApiModelProperty(value = "总库存数")
    private Integer totalInventory;

    @ApiModelProperty(value = "可借数量")
    private Integer availableBooks;
}
