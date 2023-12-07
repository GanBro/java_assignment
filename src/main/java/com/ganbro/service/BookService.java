package com.ganbro.service;

import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    List<BookInfo> searchBooks(String query);

    List<BookInfo> searchBooksByPage(String query, int pageNum, int pageSize);

    int addBookInfo(BookInfo bookInfo);

    int getBooksCountByQuery(String query);

    String selectNameById(Integer bookId);

    void deleteById(Integer bookId);

    boolean updateByBookInfo(BookInfo bookInfo);

    void insertByBookBookInfo(BookInfo bookInfo);
}
