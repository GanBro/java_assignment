package com.ganbro.controller;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.Result;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
//@Api(tags = "图书接口", description = "提供图书相关的接口")
@Api(tags = "图书接口")
public class BookController {

    private final BookService bookService;

    // 搜索图书信息
//    @GetMapping("/search")
//    public Result<List<BookInfo>> searchBooks(@RequestParam String query) {
//        List<BookInfo> books = bookService.searchBooks(query);
//        return Result.success(books);
//    }

    // 分页搜索图书信息
    @GetMapping("/searchByPage")
    @ApiOperation(value = "分页搜索图书信息")
    public Result<PageData<BookInfo>> searchBooksByPage(String query, int pageNum, int pageSize) {
        List<BookInfo> books = bookService.searchBooksByPage(query, pageNum, pageSize);
        int total = bookService.getBooksCountByQuery(query); // 获取总记录数

        PageData<BookInfo> pageData = new PageData<>();
        pageData.setTotalItems(total);
        pageData.setPageSize(pageSize);
        pageData.setCurrentPage(pageNum);
        pageData.setList(books);
        System.out.println(pageData);
        return Result.success(pageData);
    }


    // 添加图书信息
    @PostMapping
    @ApiOperation(value = "添加图书信息")
    public Result<Void> addBook(@RequestBody BookInfo bookInfo) {
        // 调用 BookService 中的方法实现添加图书的逻辑
        bookInfo.setAvailableBooks(bookInfo.getTotalInventory()); // 刚添加的可借阅数量等于图书库存
        int cnt = bookService.addBookInfo(bookInfo);
        if (cnt == 0) {
            return Result.error("添加图书失败");
        }

        return Result.success(null, "添加成功");
    }

    // 修改图书信息
    @PutMapping("/{bookId}")
    public Result<Void> updateBook(@PathVariable Long bookId, @RequestBody BookInfo bookInfo) {
        // 调用 BookService 中的方法实现修改图书的逻辑
        // ...
        return Result.success(null);
    }

    // 删除图书信息
    @DeleteMapping("/{bookId}")
    public Result<Void> deleteBook(@PathVariable Long bookId) {
        // 调用 BookService 中的方法实现删除图书的逻辑
        // ...
        return Result.success(null);
    }

    // 获取图书详细信息
    @GetMapping("/{bookId}/details")
    public Result<BookDetail> getBookDetails(@PathVariable Long bookId) {
        // 调用 BookService 中的方法获取图书详细信息
        // ...
        return Result.success(null);
    }
}
