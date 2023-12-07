package com.ganbro.mapper;

import com.ganbro.domain.entity.BookDetail;
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

    int updateByBookInfo(@Param("bookInfo") BookInfo bookInfo, @Param("sub") int sub);

//    @Insert("INSERT INTO book_details (book_name, publisher, publish_date, is_borrowed) " +
//            "VALUES (#{bookName}, #{publisher}, #{publishDate}, #{isBorrowed})")
//    void insertByBookDetail(BookDetail bookDetail);

    List<BookInfo> selectByBookNameAndPublisher(@Param("bookName") String bookName, @Param("publisher") String publisher);

    // 查询bookDetail的id
    Integer selectBookInfoIdByBookNameAndPublisher(@Param("bookName") String bookName, @Param("publisher") String publisher);

    void addTotalInventoryById(@Param("id") int id, @Param("totalInventory") int totalInventory);

    void insertByBookInfo(BookInfo bookInfo);

    void addAvailableBooksById(@Param("id")Integer id, @Param("availableBooks")Integer availableBooks);

    @Select("select total_inventory from book_info where book_info_id = #{id}")
    int selectTotalInventoryById(Long bookInfoId);

    @Select("select * from book_details where book_id = #{id}")
    BookDetail selectBookDetailById(Integer bookId);

    List<BookDetail> selectBookDetailByBookInfo(@Param("bookInfo") BookInfo bookInfo);

    @Select("select * from book_info where book_info_id = #{bookInfoId}")
    BookInfo selectBookInfoById(Integer bookInfoId);

    void deleteBookDetailByBookInfo(@Param("bookInfo") BookInfo bookInfo);

    void insertByBookDetailAndCnt(@Param("bookDetail") BookDetail bookDetail);
}
