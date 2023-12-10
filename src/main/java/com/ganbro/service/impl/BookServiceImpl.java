package com.ganbro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ganbro.domain.common.PageData;
import com.ganbro.domain.dto.DeleteBookDetailDto;
import com.ganbro.domain.dto.OverdueDto;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.UserInfo;
import com.ganbro.mapper.BookMapper;
import com.ganbro.mapper.UserMapper;
import com.ganbro.service.BookService;
import com.ganbro.utils.LocalDateTimeUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    @Override
    public List<BookInfo> searchBooks(String query) {
        List<BookInfo> bookInfos = bookMapper.searchBooks(query);
        return bookInfos;
    }

    @Override
    public List<BookInfo> searchBooksByPage(String query, int pageNum, int pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 进行分页查询
        Page<BookInfo> page = (Page<BookInfo>) bookMapper.searchBooksByPage(query);
        // 返回查询结果
        return page.getResult();
    }

    @Override
    public int addBookInfo(BookInfo bookInfo) {
        return bookMapper.addBookInfo(bookInfo);
    }

    @Override
    public int getBooksCountByQuery(String query) {
        return bookMapper.getBooksCountByQuery(query);
    }

    @Override
    public String selectNameById(Integer bookId) {
        return bookMapper.selectNameById(bookId);
    }

    @Override
    public void deleteById(Integer bookId) {
        bookMapper.deleteById(bookId);
    }

    @Override
    public boolean updateByBookInfo(BookInfo bookInfo) {
        int sub = bookMapper.selectTotalInventoryById(bookInfo.getBookInfoId()) - bookInfo.getTotalInventory();
        int cnt = bookMapper.updateByBookInfo(bookInfo, sub);
        return cnt > 0;
    }

    @Override
    public void insertByBookBookInfo(BookInfo bookInfo) {
        // 默认可借阅数量为库存
        bookInfo.setAvailableBooks(bookInfo.getTotalInventory());
        BookDetail bookDetail = new BookDetail();
        BeanUtil.copyProperties(bookInfo, bookDetail);
        bookDetail.setIsBorrowed(false); // 默认没借出去
        List<BookInfo> bookInfos= bookMapper.selectByBookNameAndPublisher(bookDetail.getBookName(), bookDetail.getPublisher());
        // 插入cnt次
        for (int i = 0; i < bookInfo.getTotalInventory(); i++) {
            bookMapper.insertByBookDetailAndCnt(bookDetail);
        }
        if (!bookInfos.isEmpty()) { // 不为空
            Integer id = bookMapper.selectBookInfoIdByBookNameAndPublisher(bookDetail.getBookName(), bookDetail.getPublisher());
            // 增加库存数量
            bookMapper.addTotalInventoryById(id, bookInfo.getTotalInventory());
            // 增加可借阅数量
            log.info(String.valueOf(bookInfo.getAvailableBooks()));
            bookMapper.addAvailableBooksById(id, bookInfo.getAvailableBooks());
        } else {
            // 2. 新增book_info信息
            bookInfo.setAvailableBooks(bookInfo.getTotalInventory());
            bookMapper.insertByBookInfo(bookInfo);
        }
    }

    @Override
    public List<BookDetail> selectBookDetailByBookInfo(Integer bookInfoId) {
        BookInfo bookInfo = bookMapper.selectBookInfoById(bookInfoId);
        return bookMapper.selectBookDetailByBookInfo(bookInfo);
    }

    @Override
    public void deleteBookDetailsByBookId(Integer bookInfoId) {
        BookInfo bookInfo = bookMapper.selectBookInfoById(bookInfoId);
        log.error(String.valueOf(bookInfo));
        bookMapper.deleteBookDetailByBookInfo(bookInfo);
    }

    @Override
    public boolean updateByBookDetail(BookDetail bookDetail) {
        // todo
        // 判断收本名称或者出版社是否更改，执行逻辑
        BookDetail bookDetail1 = bookMapper.selectBookDetailById(bookDetail.getBookId());
        // 没更改就不用管, 改了就+1
        if (bookDetail1.getBookName().equals(bookDetail.getBookName()) &&
                bookDetail1.getPublisher().equals(bookDetail.getPublisher())) {
            // 不用管
        } else {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setBookName(bookDetail.getBookName());
            bookInfo.setPublisher(bookDetail.getPublisher());
            bookInfo.setPublishDate(bookDetail.getPublishDate());
            bookInfo.setBookInfoId(1);
            bookInfo.setAvailableBooks(1);
            bookMapper.insertByBookInfo(bookInfo);
        }
        int cnt = bookMapper.updateByBookDetail(bookDetail);
        return cnt > 0;
    }

    @Override
    public void updateBookInfoStockAndAvailabelBooks() {
        List<BookInfo> bookInfos = bookMapper.selectAllBookInfo();
        for (BookInfo bookInfo : bookInfos) {
            List<BookDetail> bookDetails = bookMapper.selectBookDetailByBookInfo(bookInfo);
            bookMapper.updateTotalInventory(bookDetails.size(), bookInfo.getBookInfoId());
            bookInfo.setAvailableBooks(bookDetails.size());
        }
    }

    @Override
    public DeleteBookDetailDto deleteDetailBookById(Integer bookId) {
        DeleteBookDetailDto deleteBookDetailDto = new DeleteBookDetailDto();
        BookDetail bookDetail = bookMapper.selectBookDetailById(bookId);
        if (bookDetail.getIsBorrowed()) {
            deleteBookDetailDto.setFlag(false);
            deleteBookDetailDto.setMessage("图书已被借出，不能删除!!!");
            return deleteBookDetailDto;
        }
        BookInfo bookInfo = bookMapper.selectBookInfo(bookDetail);
        bookInfo.setAvailableBooks(bookInfo.getAvailableBooks() - 1);
        bookMapper.updateBookInfo(bookInfo);
        bookMapper.deleteDetailBookById(bookId);
        deleteBookDetailDto.setFlag(true);
        deleteBookDetailDto.setMessage("删除成功!!!");
        return deleteBookDetailDto;
    }

    @Override
    public OverdueDto borrowBook(String username, Integer bookId) throws ParseException {
        OverdueDto overdueDto = new OverdueDto();
        BookDetail bookDetail = bookMapper.selectBookDetailById(bookId);
        BookInfo bookInfo = bookMapper.selectBookInfo(bookDetail);
        UserInfo userInfo = userMapper.selectUserInfo(username);
        if (bookDetail.getIsBorrowed()) {
            overdueDto.setMessage("此书已被借阅，请重新挑选!!!");
            overdueDto.setFlag(false);
            return overdueDto;
        }
        if (userInfo.getOverdueBooks() > 0) {
            overdueDto.setMessage("您已逾期,禁止借书!!!");
            overdueDto.setFlag(false);
            return overdueDto;
        }
        if (bookInfo.getAvailableBooks() <= 0) {
            overdueDto.setMessage("书已借完!!!");
            overdueDto.setFlag(false);
            return overdueDto;
        }
        bookInfo.setAvailableBooks(bookInfo.getAvailableBooks() - 1); // 更新可借阅的书
        bookMapper.updateBookInfo(bookInfo);
        // 已经借到的书的数量
        int haveBorrowed = userInfo.getBorrowedBooks();
        // 最大可借数量
        int maxCanBorrow = userInfo.getMaxBooksAllowed();
        if (maxCanBorrow - haveBorrowed > 0) {
            bookDetail.setIsBorrowed(true);
            bookDetail.setUserId(userInfo.getUserId());
            bookDetail.setStartDate(LocalDateTimeUtil.format(LocalDateTime.now()));
            Boolean isVip = userInfo.getIsVip();
            int add = isVip ? 90 : 30; //vip90天，普通用户30天
            bookDetail.setDueDate(LocalDateTimeUtil.format(LocalDateTime.now().plusDays(add)));
            bookMapper.updateByBookDetail(bookDetail);
            userInfo.setBorrowedBooks(userInfo.getBorrowedBooks() + 1); // 借一本
            userMapper.updateUserInfo(userInfo);
            overdueDto.setFlag(true);
            overdueDto.setMessage("借书成功!!!");
        } else {
            overdueDto.setMessage("您已超出最大可借数量!!!");
            overdueDto.setFlag(false);
        }
        return overdueDto;
    }

    @Override
    public PageData<BookDetail> searchBookDetail(String username, Integer currentPage, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(currentPage, pageSize);
        // 进行分页查询
        UserInfo userInfo = userMapper.selectUserInfo(username);
        List<BookDetail> bookDetails = bookMapper.selectBookDetail(userInfo.getUserId());
        // 封装分页结果
        PageInfo<BookDetail> pageInfo = new PageInfo<>(bookDetails);

        // 构建返回结果对象
        PageData<BookDetail> pageData = new PageData<>();
        pageData.setList(pageInfo.getList());
        pageData.setTotalItems(pageInfo.getTotal());
        pageData.setCurrentPage(pageInfo.getPageNum());
        pageData.setPageSize(pageInfo.getPageSize());

        // 返回查询结果
        return pageData;
    }



}
