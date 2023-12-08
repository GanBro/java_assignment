package com.ganbro.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "图书详情", description = "图书详细信息实体类")
public class BookDetail {

    @ApiModelProperty(value = "图书ID")
    private Integer bookId;

    @ApiModelProperty(value = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "出版社")
    private String publisher;

    @ApiModelProperty(value = "出版日期")
    private String publishDate;

    @ApiModelProperty(value = "是否已借阅")
    private Boolean isBorrowed;

    @ApiModelProperty(value = "借阅用户ID")
    private Integer userId;

    @ApiModelProperty(value = "借阅开始日期")
    private String startDate;

    @ApiModelProperty(value = "借阅到期日期")
    private String dueDate;
}
