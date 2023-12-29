package com.ganbro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.Result;
import com.ganbro.domain.common.ReturnModel;
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
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
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
    @Transactional
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
    @Transactional
    public void deleteById(Integer bookId) {
        bookMapper.deleteById(bookId);
    }

    @Override
    @Transactional
    public boolean updateByBookInfo(BookInfo bookInfo) {
        int sub = bookMapper.selectTotalInventoryById(bookInfo.getBookInfoId()) - bookInfo.getTotalInventory();
        int cnt = bookMapper.updateByBookInfo(bookInfo, sub);
        return cnt > 0;
    }

    @Override
    @Transactional
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
    public PageData<BookDetail> selectBookDetailByBookInfo(Integer bookInfoId, Integer currentPageDetail, Integer pageSizeDetail) {
        BookInfo bookInfo = bookMapper.selectBookInfoById(bookInfoId);
        PageHelper.startPage(currentPageDetail, pageSizeDetail);

        PageData<BookDetail> pageData = new PageData<>();
        List<BookDetail> bookDetails = bookMapper.selectBookDetailByBookInfo(bookInfo);
        PageInfo<BookDetail> bookDetailPageInfo = new PageInfo<>(bookDetails);
        pageData.setList(bookDetailPageInfo.getList());
        pageData.setTotalItems(bookDetailPageInfo.getTotal());
        return pageData;
    }

    @Override
    @Transactional
    public void deleteBookDetailsByBookId(Integer bookInfoId) {
        BookInfo bookInfo = bookMapper.selectBookInfoById(bookInfoId);
        log.error(String.valueOf(bookInfo));
        bookMapper.deleteBookDetailByBookInfo(bookInfo);
    }

    @Override
    @Transactional
    public ReturnModel updateByBookDetail(BookDetail bookDetail) {
        ReturnModel returnModel = new ReturnModel();
        if (bookDetail.getUserId() != null) {
            returnModel.setFlag(false);
            returnModel.setMessage("已被借出的书籍不允许修改!!!");
            return returnModel;
        }
        // 判断收本名称或者出版社是否更改，执行逻辑
        BookDetail bookDetail1 = bookMapper.selectBookDetailById(bookDetail.getBookId());
        // 没更改就不用管, 改了就+1
        if (bookDetail1.getBookName().equals(bookDetail.getBookName()) &&
                bookDetail1.getPublisher().equals(bookDetail.getPublisher())) {
            // 不用管
        } else {
            // 1. 修改原来的库存
            // 1.1 找到原来的BookDetail
            BookDetail bookDetail2 = bookMapper.selectBookDetailById(bookDetail.getBookId());
            // 1.2 找到原来的BookInfo
            BookInfo bookInfo1 = bookMapper.selectBookInfo(bookDetail2);
            // 1.3 修改库存
            bookInfo1.setAvailableBooks(bookInfo1.getAvailableBooks() - 1);
            bookInfo1.setTotalInventory(bookInfo1.getTotalInventory() - 1);
            // 1.4 判断删除后库存是否为0，如果是就删除，否则修改
            if (bookInfo1.getAvailableBooks() == 0) {
                bookMapper.deleteBookInfo(bookInfo1);
            } else {
                bookMapper.updateBookInfo(bookInfo1);
            }
            // 2. 新增图书逻辑
            BookInfo bookInfo = new BookInfo();
            bookInfo.setBookName(bookDetail.getBookName());
            bookInfo.setPublisher(bookDetail.getPublisher());
            bookInfo.setPublishDate(bookDetail.getPublishDate());
            bookInfo.setBookInfoId(1);
            bookInfo.setAvailableBooks(1);
            BookInfo bookInfo2 = bookMapper.selectBookInfo(bookDetail); // 原来的bookInfo
            if (bookInfo2 != null) {
                // 存在图书分类就把原来的BookInfo增加
                bookInfo2.setAvailableBooks(bookInfo2.getAvailableBooks() + 1);
                bookInfo2.setTotalInventory(bookInfo2.getTotalInventory() + 1);
                bookMapper.updateBookInfo(bookInfo2);
            } else {
                // 否则新增
                bookMapper.insertByBookInfo(bookInfo);
            }
        }
        bookMapper.updateByBookDetail(bookDetail);
        returnModel.setFlag(true);
        returnModel.setMessage("修改成功");
        return returnModel;
    }

    @Override
    @Transactional
    public void updateBookInfoStockAndAvailabelBooks() {
        List<BookInfo> bookInfos = bookMapper.selectAllBookInfo();
        for (BookInfo bookInfo : bookInfos) {
            List<BookDetail> bookDetails = bookMapper.selectBookDetailByBookInfo(bookInfo);
            bookMapper.updateTotalInventory(bookDetails.size(), bookInfo.getBookInfoId());
            bookInfo.setAvailableBooks(bookDetails.size());
        }
    }

    @Override
    @Transactional
    public ReturnModel deleteDetailBookById(Integer bookId) {
        ReturnModel returnModel = new ReturnModel();
        BookDetail bookDetail = bookMapper.selectBookDetailById(bookId);
        if (bookDetail.getIsBorrowed()) {
            returnModel.setFlag(false);
            returnModel.setMessage("图书已被借出，不能删除!!!");
            return returnModel;
        }
        BookInfo bookInfo = bookMapper.selectBookInfo(bookDetail);
        bookInfo.setAvailableBooks(bookInfo.getAvailableBooks() - 1);
        bookMapper.updateBookInfo(bookInfo);
        bookMapper.deleteDetailBookById(bookId);
        if (bookInfo.getTotalInventory() == 0) {
            bookMapper.deleteBookInfo(bookInfo);
        }
        returnModel.setFlag(true);
        returnModel.setMessage("删除成功!!!");
        return returnModel;
    }

    @Override
    @Transactional
    public ReturnModel borrowBook(String username, Integer bookId) throws ParseException {
        ReturnModel returnModel = new ReturnModel();
        BookDetail bookDetail = bookMapper.selectBookDetailById(bookId);
        BookInfo bookInfo = bookMapper.selectBookInfo(bookDetail);
        UserInfo userInfo = userMapper.selectUserInfo(username);
        if (bookDetail.getIsBorrowed()) {
            returnModel.setMessage("此书已被借阅，请重新挑选!!!");
            returnModel.setFlag(false);
            return returnModel;
        }
        if (userInfo.getOverdueBooks() > 0) {
            returnModel.setMessage("您已逾期,禁止借书!!!");
            returnModel.setFlag(false);
            return returnModel;
        }
        if (bookInfo.getAvailableBooks() <= 0) {
            returnModel.setMessage("书已借完!!!");
            returnModel.setFlag(false);
            return returnModel;
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
            returnModel.setFlag(true);
            returnModel.setMessage("借书成功!!!");
        } else {
            returnModel.setMessage("您已超出最大可借数量!!!");
            returnModel.setFlag(false);
        }
        return returnModel;
    }

    @Override
    public PageData<BookDetail> searchBookDetail(String username, Integer currentPage, Integer pageSize) {
        // 进行分页查询
        UserInfo userInfo = userMapper.selectUserInfo(username);
        // 设置分页参数
        PageHelper.startPage(currentPage, pageSize);
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

    @Override
    @Transactional
    public ReturnModel returnBook(Integer bookId) {
        ReturnModel returnModel = new ReturnModel();
        BookDetail bookDetail = bookMapper.selectBookDetailById(bookId);
        BookInfo bookInfo = bookMapper.selectBookInfo(bookDetail);
        bookInfo.setAvailableBooks(bookInfo.getAvailableBooks() + 1);
        int userId = bookDetail.getUserId();
        UserInfo userInfo = userMapper.selectUserInfoByUserId(userId);
        userInfo.setBorrowedBooks(userInfo.getBorrowedBooks() - 1);
        // 如果归还的图书是逾期的图书，则不能归还
        String dueDateString = bookDetail.getDueDate();
        if (dueDateString.compareTo(LocalDateTime.now().toString()) < 0) {
            returnModel.setFlag(false);
            returnModel.setMessage("该图书已逾期，不能归还，请联系管理员!!!");
            return returnModel;
        }
        userMapper.updateUserInfo(userInfo);
        bookMapper.updateBookInfo(bookInfo);
        bookMapper.returnBookDetails(bookId);
        returnModel.setFlag(true);
        returnModel.setMessage("归还成功！");
        return returnModel;
    }

    @Override
    public ReturnModel deleteBook(Integer bookInfoId) {
        ReturnModel returnModel = new ReturnModel();
        bookMapper.selectNameById(bookInfoId);
        BookInfo bookInfo = bookMapper.selectBookInfoById(bookInfoId);
        List<BookDetail> bookDetails = bookMapper.selectBookDetailByBookInfo(bookInfo);
        for (BookDetail bookDetail : bookDetails) {
            if (bookDetail.getIsBorrowed()) {
                returnModel.setMessage("该图书正在被借阅，不能删除！");
                returnModel.setFlag(false);
                return returnModel;
            }
        }
        deleteBookDetailsByBookId(bookInfoId);
        deleteById(bookInfoId);
        returnModel.setFlag(true);
        returnModel.setMessage("删除成功!!!");
        return returnModel;
    }


}
