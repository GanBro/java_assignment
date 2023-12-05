package com.ganbro.service.impl;

import com.ganbro.domain.entity.BookInfo;
import com.ganbro.mapper.BookMapper;
import com.ganbro.service.BookService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
}
