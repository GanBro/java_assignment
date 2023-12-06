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

    @Select("SELECT COUNT(*) FROM book_info WHERE book_name LIKE CONCAT('%', #{query}, '%')")
    int getBooksCountByQuery(String query);

    @Select("select book_name from book_info where book_info_id = #{bookId}")
    String selectNameById(Integer bookId);

    @Delete("delete from book_info where book_info_id = #{bookId}")
    void deleteById(Integer bookId);

    @Update("UPDATE book_info SET book_name = #{bookName}, publisher = #{publisher}, " +
            "publish_date = #{publishDate}, total_inventory = #{totalInventory}" +
            " WHERE book_info_id = #{bookInfoId}")
    int updateByBookInfo(BookInfo bookInfo);

}
