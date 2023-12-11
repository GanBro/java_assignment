package com.ganbro.controller;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.Result;
import com.ganbro.domain.common.ReturnModel;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
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
    public Result<ReturnModel> updateBookDetail(@RequestBody BookDetail bookDetail) {
        ReturnModel returnModel = bookService.updateByBookDetail(bookDetail);
        if (returnModel.getFlag()) {
            return Result.success(null,returnModel.getMessage());  // 返回成功结果
        } else {
            return Result.error(null,returnModel.getMessage());  // 返回失败结果
        }
    }

    @DeleteMapping("/{bookId}")
    @ApiOperation(value = "删除图书信息")
    public Result<Void> deleteBook(@PathVariable("bookId") Integer bookInfoId) {
        String name = bookService.selectNameById(bookInfoId);
        List<BookDetail> bookDetails = bookService.selectBookDetailByBookInfo(bookInfoId);
        for (BookDetail bookDetail : bookDetails) {
            if (bookDetail.getIsBorrowed()) {
                return Result.error(null, "图书" + name + "已被借出，无法删除");
            }
        }
        bookService.deleteBookDetailsByBookId(bookInfoId);
        bookService.deleteById(bookInfoId);
        return Result.success(null, "删除图书" + name + "成功");
    }

    @DeleteMapping("/detail/{bookId}")
    @ApiOperation(value = "删除详细图书")
    public Result<Void> deleteDetailBook(@PathVariable Integer bookId) {
        ReturnModel returnModel = bookService.deleteDetailBookById(bookId);
        if (returnModel.getFlag()) {
            return Result.success(null, returnModel.getMessage());
        } else {
            return Result.error(null, returnModel.getMessage());
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
        ReturnModel overdueDto = bookService.borrowBook(username, bookId);
        if (overdueDto.getFlag()) {
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

    @PutMapping("/returnBookDetail/{bookId}")
    @ApiOperation(value = "归还图书")
    public Result<Void> returnBook(@PathVariable Integer bookId) {
        bookService.returnBook(bookId);
        return Result.success(null, "归还成功");
    }

    // todo 用户管理管理界面更新所有人的逾期 修改VIP状态时更新其他属性 用户借阅书本数量改变时判断是否可改变逻辑
    // todo 借书页面过期时更新逾期
    // todo 用户管理改用户名出错
}
