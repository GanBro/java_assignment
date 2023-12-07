package com.ganbro.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    private Integer bookInfoId;
    private String bookName;
    private String publisher;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    // 数据库是Date型，这里用String简便代替
    private String publishDate;
    private Integer totalInventory;
    private Integer availableBooks;
}
