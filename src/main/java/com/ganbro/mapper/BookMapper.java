package com.ganbro.mapper;

import com.ganbro.domain.entity.BookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("SELECT * FROM book_info WHERE book_name LIKE CONCAT('%', #{query}, '%')")
    List<BookInfo> searchBooks(String query);
}
