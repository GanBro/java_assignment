package com.ganbro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetail {
    private Long bookId;
    private String bookName;
    private String publisher;
    private String publishDate;
    private Boolean isBorrowed;
    private Long userId;
    // 数据库是Date型，这里用String简便代替
    private String startDate;
    private String dueDate;
}
