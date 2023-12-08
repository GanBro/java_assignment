package com.ganbro.service.impl;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.User;
import com.ganbro.mapper.UserMapper;
import com.ganbro.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Override
    public PageData<User> searchUsersByPage(String query, int pageNum, int pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 进行分页查询
        List<User> userList = userMapper.searchUsersByQuery(query);
        // 获取分页信息
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        // 构建PageData对象
        PageData<User> pageData = new PageData<>();
        pageData.setList(userList);
        pageData.setTotalItems(pageInfo.getTotal());
        pageData.setPageSize(pageInfo.getPageSize());
        pageData.setCurrentPage(pageInfo.getPageNum());
        return pageData;
    }
}
