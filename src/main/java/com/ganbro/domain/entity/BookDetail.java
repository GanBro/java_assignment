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
    private Date startDate;
    private Date dueDate;
}
