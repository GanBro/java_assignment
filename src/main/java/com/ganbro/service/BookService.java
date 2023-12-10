package com.ganbro.service;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.dto.DeleteBookDetailDto;
import com.ganbro.domain.dto.OverdueDto;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

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

    List<BookDetail> selectBookDetailByBookInfo(Integer bookInfoId);

    void deleteBookDetailsByBookId(Integer bookInfoId);

    boolean updateByBookDetail(BookDetail bookDetail);

    void updateBookInfoStockAndAvailabelBooks();

    DeleteBookDetailDto deleteDetailBookById(Integer bookId);

    /*返回消息*/
    OverdueDto borrowBook(String username, Integer bookId) throws ParseException;

    PageData<BookDetail> searchBookDetail(String username, Integer currentPage, Integer pageSize);
}
