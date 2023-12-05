package com.ganbro.controller;

import com.ganbro.domain.common.Result;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class BookController {

    private final BookService bookService;

    // 搜索图书信息
    @GetMapping("/search")
    public Result<List<BookInfo>> searchBooks(@RequestParam String query) {
        List<BookInfo> books = bookService.searchBooks(query);
        return Result.success(books);
    }

    // 分页搜索图书信息
    @GetMapping("/searchByPage")
    public Result<List<BookInfo>> searchBooksByPage(String query,
                                                    @RequestParam(defaultValue = "1") int pageNum,
                                                    @RequestParam(defaultValue = "10") int pageSize) {
        List<BookInfo> books = bookService.searchBooksByPage(query, pageNum, pageSize);
        return Result.success(books);
    }

    // 添加图书信息
    @PostMapping
    public Result<Void> addBook(@RequestBody BookInfo bookInfo) {
        // 调用 BookService 中的方法实现添加图书的逻辑
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
