package com.ganbro.service.impl;

import com.ganbro.domain.entity.BookInfo;
import com.ganbro.mapper.BookMapper;
import com.ganbro.service.BookService;
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
}
