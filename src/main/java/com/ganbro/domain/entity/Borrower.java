package com.ganbro.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrower {
    private Long userId;
    private String userName;
    private Integer maxBooksAllowed;
    private Integer borrowedBooks;
    private Boolean isVip;
    private Integer overdueBooks;
}
