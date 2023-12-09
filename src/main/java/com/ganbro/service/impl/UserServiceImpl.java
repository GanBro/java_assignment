package com.ganbro.service.impl;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.User;
import com.ganbro.domain.entity.UserInfo;
import com.ganbro.mapper.UserMapper;
import com.ganbro.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public PageData<UserInfo> searchUserInfoByPage(String query, int pageNum, int pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 进行分页查询
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
        }
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
}
