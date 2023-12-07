package com.ganbro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.mapper.BookMapper;
import com.ganbro.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
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
        log.error(String.valueOf(bookInfo));
        log.error(String.valueOf(sub));
        int cnt = bookMapper.updateByBookInfo(bookInfo, sub);
        return cnt > 0;
    }
/*    @Override
    public void insertByBookDetail(BookDetail bookDetail) {
        bookDetail.setIsBorrowed(false); // 默认没借出去
        // 1. 判断书是否存在
        List<BookInfo> bookInfos= bookMapper.selectByBookNameAndPublisher(bookDetail.getBookName(), bookDetail.getPublisher());
        log.error(bookInfos.toString());
        if (!bookInfos.isEmpty()) { // 不为空
            Integer id = bookMapper.selectBookInfoIdByBookNameAndPublisher(bookDetail.getBookName(), bookDetail.getPublisher());
            bookMapper.addTotalInventoryById(id);
        } else {
            // 1. 详细表插入
            bookMapper.insertByBookDetail(bookDetail);
            // 2. 新增book_info信息
            BookInfo bookInfo = new BookInfo();
            BeanUtil.copyProperties(bookDetail, bookInfo);
            bookInfo.setAvailableBooks(bookInfo.getTotalInventory());
            bookMapper.insertByBookInfo(bookInfo);
        }
    }*/

    @Override
    public void insertByBookBookInfo(BookInfo bookInfo) {
        // 默认可借阅数量为库存
        bookInfo.setAvailableBooks(bookInfo.getTotalInventory());
        BookDetail bookDetail = new BookDetail();
        BeanUtil.copyProperties(bookInfo, bookDetail);
        bookDetail.setIsBorrowed(false); // 默认没借出去
        List<BookInfo> bookInfos= bookMapper.selectByBookNameAndPublisher(bookDetail.getBookName(), bookDetail.getPublisher());
//        log.error(bookInfos.toString());
        bookMapper.insertByBookDetail(bookDetail); // 无论是否上面为空都要插入
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


}
