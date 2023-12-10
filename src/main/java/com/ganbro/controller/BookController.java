package com.ganbro.controller;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.Result;
import com.ganbro.domain.dto.DeleteBookDetailDto;
import com.ganbro.domain.dto.OverdueDto;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.awt.print.Book;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
@Slf4j
@Api(tags = "图书接口")
public class BookController {

    private final BookService bookService;

    // 分页搜索图书信息
    @GetMapping("/searchByPage")
    @ApiOperation(value = "分页搜索图书信息")
    public Result<PageData<BookInfo>> searchBooksByPage(String query, int pageNum, int pageSize) {
        log.error(query);
        // 更新所有BookInfo的库存和可借阅数量
        bookService.updateBookInfoStockAndAvailabelBooks();
        List<BookInfo> books = bookService.searchBooksByPage(query, pageNum, pageSize);
        int total = bookService.getBooksCountByQuery(query); // 获取总记录数
        PageData<BookInfo> pageData = new PageData<>();
        pageData.setTotalItems(total);
        pageData.setPageSize(pageSize);
        pageData.setCurrentPage(pageNum);
        pageData.setList(books);
        return Result.success(pageData);
    }

    // 新增图书信息
    @PostMapping
    @ApiOperation(value = "添加图书信息")
    public Result<Void> addBook(@RequestBody BookInfo bookInfo) {
        bookService.insertByBookBookInfo(bookInfo);
        return Result.success(null,"添加成功");
    }

    @PutMapping
    @ApiOperation(value = "修改图书信息")
    public Result<Void> updateBookInfo(@RequestBody BookInfo bookInfo) {
        boolean success = bookService.updateByBookInfo(bookInfo);  // 调用 Service 层方法进行更新
        if (success) {
            return Result.success(null,"修改图书信息成功");  // 返回成功结果
        } else {
            return Result.error(null, "修改图书信息失败");  // 返回失败结果
        }
    }

    @PutMapping("/detail")
    @ApiOperation(value = "修改图书详细信息")
    public Result<Void> updateBookDetail(@RequestBody BookDetail bookDetail) {
        boolean success = bookService.updateByBookDetail(bookDetail);  // 调用 Service 层方法进行更新
        if (success) {
            return Result.success(null,"修改图书信息成功");  // 返回成功结果
        } else {
            return Result.error(null,"修改图书信息失败");  // 返回失败结果
        }
    }

    @DeleteMapping("/{bookId}")
    @ApiOperation(value = "删除图书信息")
    public Result<Void> deleteBook(@PathVariable("bookId") Integer bookInfoId) {
        String name = bookService.selectNameById(bookInfoId);
        bookService.deleteBookDetailsByBookId(bookInfoId);
        bookService.deleteById(bookInfoId);
        return Result.success(null, "删除图书" + name + "成功");
    }

    @DeleteMapping("/detail/{bookId}")
    @ApiOperation(value = "删除详细图书")
    public Result<Void> deleteDetailBook(@PathVariable Integer bookId) {
        DeleteBookDetailDto deleteBookDetailDto = bookService.deleteDetailBookById(bookId);
        if (deleteBookDetailDto.getFlag()) {
            return Result.success(null, deleteBookDetailDto.getMessage());
        } else {
            return Result.error(null, deleteBookDetailDto.getMessage());
        }
    }

    @GetMapping("/{bookInfoId}/details")
    @ApiOperation(value = "获取图书详细信息")
    public Result<List<BookDetail>> getBookDetails(@PathVariable Integer bookInfoId) {
        // 调用 BookService 中的方法获取图书详细信息
        List<BookDetail> bookDetails = bookService.selectBookDetailByBookInfo(bookInfoId);
        return Result.success(bookDetails);
    }

    @PostMapping("/borrow/{username}")
    @ApiOperation(value = "借阅图书")
    public Result<Void> borrowBook(@PathVariable String username, @PathParam("bookId") Integer bookId) throws ParseException {
        OverdueDto overdueDto = bookService.borrowBook(username, bookId);
        if (overdueDto.isFlag()) {
            return Result.success(null, overdueDto.getMessage());
        } else {
            return Result.error(null, overdueDto.getMessage());
        }

    }

    @GetMapping("/searchBookDetail/{username}")
    @ApiOperation(value = "借阅图书详情展示")
    public Result<PageData<BookDetail>> searchBookDetail(@PathVariable String username,
                                                         @RequestParam("currentPage") Integer currentPage,
                                                         @RequestParam("pageSize") Integer pageSize) {
        PageData<BookDetail> bookDetails = bookService.searchBookDetail(username, currentPage, pageSize);
        return Result.success(bookDetails);
    }
    // todo 判断逾期 用户管理管理界面更新所有人的逾期 修改VIP状态时更新其他属性
}
