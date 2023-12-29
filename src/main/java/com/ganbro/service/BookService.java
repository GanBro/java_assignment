package com.ganbro.service;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.ReturnModel;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;

import java.text.ParseException;
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

    PageData<BookDetail> selectBookDetailByBookInfo(Integer bookInfoId, Integer currentPage, Integer pageSize);

    void deleteBookDetailsByBookId(Integer bookInfoId);

    ReturnModel updateByBookDetail(BookDetail bookDetail);

    void updateBookInfoStockAndAvailabelBooks();

    ReturnModel deleteDetailBookById(Integer bookId);

    /*返回消息*/
    ReturnModel borrowBook(String username, Integer bookId) throws ParseException;

    PageData<BookDetail> searchBookDetail(String username, Integer currentPage, Integer pageSize);

    ReturnModel returnBook(Integer bookId);

    ReturnModel deleteBook(Integer bookInfoId);
}
