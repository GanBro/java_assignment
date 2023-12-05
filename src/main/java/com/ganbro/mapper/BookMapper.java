package com.ganbro.mapper;

import com.ganbro.domain.entity.BookInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("SELECT * FROM book_info WHERE book_name LIKE CONCAT('%', #{query}, '%')")
    List<BookInfo> searchBooks(String query);

    // 分页查询
    @Select("SELECT * FROM book_info WHERE book_name LIKE CONCAT('%', #{query}, '%')")
    Page<BookInfo> searchBooksByPage(@Param("query") String query);

    @Insert("INSERT INTO book_info (book_name, publisher, publish_date, total_inventory, available_books) " +
            "VALUES (#{bookName}, #{publisher}, #{publishDate}, #{totalInventory}, #{availableBooks})")
    @Options(useGeneratedKeys = true, keyProperty = "bookInfoId")
    int addBookInfo(BookInfo bookInfo);
}
