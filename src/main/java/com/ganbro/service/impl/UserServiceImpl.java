package com.ganbro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.ReturnModel;
import com.ganbro.domain.entity.BookDetail;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.User;
import com.ganbro.domain.entity.UserInfo;
import com.ganbro.mapper.BookMapper;
import com.ganbro.mapper.UserMapper;
import com.ganbro.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public PageData<UserInfo> searchUserInfoByPage(String query, int pageNum, int pageSize) {
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
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
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
    @Transactional
    public ReturnModel deleteUserById(Integer userId) {
        UserInfo userInfo = userMapper.selectUserInfoByUserId(userId);
        ReturnModel returnModel = new ReturnModel();
        if (userInfo.getBorrowedBooks() > 0) {
            returnModel.setFlag(false);
            returnModel.setMessage("该用户还有书未归还，不能删除!!!");
        } else {
            userMapper.deleteUserInfoById(userId);
            userMapper.deleteUserById(userId);
            returnModel.setFlag(true);
            returnModel.setMessage("删除成功");
        }
        return returnModel;
    }

    @Override
    @Transactional
    public ReturnModel updateUserInfo(UserInfo userInfo) {
        ReturnModel returnModel = new ReturnModel();
        if (userInfo.getBorrowedBooks() > userInfo.getMaxBooksAllowed()) {
            returnModel.setFlag(false);
            returnModel.setMessage("修改失败!用户可借阅书本数量不能小于已借阅数量");
        } else {
            userMapper.updateUsers(userInfo);
            userMapper.updateUserInfo(userInfo);
            returnModel.setFlag(true);
            returnModel.setMessage("修改用户信息成功");
        }
        return returnModel;
    }

    @Override
    public ReturnModel addUserInfo(UserInfo userInfo) {
        ReturnModel returnModel = new ReturnModel();
        UserInfo userInfo1 = userMapper.selectUserInfo(userInfo.getUsername());
        if (BeanUtil.isNotEmpty(userInfo1)) {
            returnModel.setFlag(false);
            returnModel.setMessage("存在该用户，请勿重复添加!!!");
            return returnModel;
        }
        returnModel.setFlag(true);
        returnModel.setMessage("添加用户信息成功!");

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
//        user.setPassword(user.getUsername());
        user.setPassword("123456");
        user.setIsAdmin(false); // 默认为普通用户
        userMapper.insertUser(user);
        Integer id = user.getUserId();
        userInfo.setUserId(id);
        userMapper.insertUserInfo(userInfo);
        return returnModel;
    }

    @Override
    public UserInfo selectUserInfo(String username) {
        return userMapper.selectUserInfo(username);
    }

    @Override
    @Transactional
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
        userInfo.setOverdueBooks(overdueBooks); // 设置到期的书(已逾期的书不能消除)
        userMapper.updateUserInfo(userInfo);
    }

    @Override
    public void resetDueBooks(String username) {
        UserInfo userInfo = userMapper.selectUserInfo(username);
        int userId = userInfo.getUserId();
        List<BookDetail> bookDetails = bookMapper.selectBookDetail(userId);
        BookInfo bookInfo = bookMapper.selectBookInfo(bookDetails.get(0)); // 通过一个detail找到bookInfo
        for (BookDetail bookDetail: bookDetails) {
            LocalDateTime time = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dueDate = LocalDate.parse(bookDetail.getDueDate(), dateFormatter);
            if (dueDate.isBefore(time.toLocalDate())) {
                bookDetail.setIsBorrowed(false);
                bookDetail.setStartDate(null);
                bookDetail.setUserId(null);
                bookDetail.setDueDate(null);
                bookMapper.updateByBookDetail(bookDetail);
                userInfo.setBorrowedBooks(userInfo.getBorrowedBooks() - 1); // 还一本
                bookInfo.setAvailableBooks(bookInfo.getAvailableBooks() + 1); // 库存多一本
            }
        }
        bookMapper.updateBookInfo(bookInfo);
        userMapper.updateUserInfo(userInfo);
    }
}
