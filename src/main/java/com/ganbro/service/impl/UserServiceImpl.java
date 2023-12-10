package com.ganbro.service.impl;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.User;
import com.ganbro.domain.entity.UserInfo;
import com.ganbro.mapper.BookMapper;
import com.ganbro.mapper.UserMapper;
import com.ganbro.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Override
    public PageData<UserInfo> searchUserInfoByPage(String query, int pageNum, int pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 进行分页查询
        List<UserInfo> userList1 = userMapper.searchUserInfoByQuery(query);

        // 更新逾期状态
        for (UserInfo userInfo : userList1) {
            List<BookDetail> bookDetails = bookMapper.selectBookDetail(userInfo.getUserId());
            int overdueBooks = 0;
            for (BookDetail b : bookDetails) {
                LocalDateTime time = LocalDateTime.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 定义日期格式
                LocalDate dueDate = LocalDate.parse(b.getDueDate(), dateFormatter); // 解析日期字符串为 LocalDate 对象
                LocalDateTime dueDateTime = dueDate.atStartOfDay(); // 转换为 LocalDateTime 对象，时间设为当天的午夜
                if (time.isAfter(dueDateTime) || time.isEqual(dueDateTime)) {
                    overdueBooks = overdueBooks + 1;
                }
            }
            userInfo.setOverdueBooks(overdueBooks); // 设置到期的书
            userMapper.updateUserInfo(userInfo);
        }

        // 更新后再执行一次
        List<UserInfo> userList = userMapper.searchUserInfoByQuery(query);

        // 获取分页信息
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userList);
        // 构建PageData对象
        PageData<UserInfo> pageData = new PageData<>();
        pageData.setList(userList);
        pageData.setTotalItems(pageInfo.getTotal());
        pageData.setPageSize(pageInfo.getPageSize());
        pageData.setCurrentPage(pageInfo.getPageNum());
        return pageData;
    }

    @Override
    public void deleteUserById(Integer userId) {
        userMapper.deleteUserById(userId);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userMapper.updateUsers(userInfo);
        userMapper.updateUserInfo(userInfo);
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        if (userInfo.getIsVip() == null) {
            userInfo.setIsVip(false);
            userInfo.setMaxBooksAllowed(5);
        } else {
            userInfo.setMaxBooksAllowed(10);
        }
        userInfo.setBorrowedBooks(0);
        userInfo.setOverdueBooks(0);
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setPassword(user.getUsername());
        user.setIsAdmin(false); // 默认为普通用户
        userMapper.insertUser(user);
        Integer id = user.getUserId();
        userInfo.setUserId(id);
        userMapper.insertUserInfo(userInfo);
    }

    @Override
    public UserInfo selectUserInfo(String username) {
        return userMapper.selectUserInfo(username);
    }

    @Override
    public void updateDueBooks(String username) {
        UserInfo userInfo = userMapper.selectUserInfo(username);
        // 更新逾期状态
        List<BookDetail> bookDetails = bookMapper.selectBookDetail(userInfo.getUserId());
        int overdueBooks = 0;
        for (BookDetail b : bookDetails) {
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 定义日期格式
            LocalDate dueDate = LocalDate.parse(b.getDueDate(), dateFormatter); // 解析日期字符串为 LocalDate 对象
            LocalDateTime dueDateTime = dueDate.atStartOfDay(); // 转换为 LocalDateTime 对象，时间设为当天的午夜
            if (time.isAfter(dueDateTime) || time.isEqual(dueDateTime)) {
                overdueBooks = overdueBooks + 1;
            }
        }
        userInfo.setOverdueBooks(overdueBooks); // 设置到期的书
        userMapper.updateUserInfo(userInfo);
    }
}
