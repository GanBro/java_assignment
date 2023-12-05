package com.ganbro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    private Long bookInfoId;
    private String bookName;
    private String publisher;
    private String publishDate;
    private int totalInventory;
    private int availableBooks;
}
