package com.ganbro.controller;

import com.ganbro.domain.common.Result;
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

    // 获取所有图书信息
//    @GetMapping
//    public Result<List<BookInfo>> getAllBooks() {
//        List<BookInfo> books = bookService.getAllBooks();
//        return Result.success(books);
//    }

    // 搜索图书信息
    @GetMapping("/search")
    public Result<List<BookInfo>> searchBooks(@RequestParam String query) {
        List<BookInfo> books = bookService.searchBooks(query);
        return Result.success(books);
    }

    // 添加图书信息
//    @PostMapping
//    public Result<Void> addBook(@RequestBody BookInfoRequest bookRequest) {
//        bookService.addBook(bookRequest);
//        return Result.success(null);
//    }
//
//    // 修改图书信息
//    @PutMapping("/{bookId}")
//    public Result<Void> updateBook(@PathVariable Long bookId, @RequestBody BookInfoRequest bookRequest) {
//        bookService.updateBook(bookId, bookRequest);
//        return Result.success(null);
//    }
//
//    // 删除图书信息
//    @DeleteMapping("/{bookId}")
//    public Result<Void> deleteBook(@PathVariable Long bookId) {
//        bookService.deleteBook(bookId);
//        return Result.success(null);
//    }
//
//    // 获取图书详细信息
//    @GetMapping("/{bookId}/details")
//    public Result<BookDetails> getBookDetails(@PathVariable Long bookId) {
//        BookDetails bookDetails = bookService.getBookDetails(bookId);
//        return Result.success(bookDetails);
//    }
}
