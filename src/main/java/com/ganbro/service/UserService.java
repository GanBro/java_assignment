package com.ganbro.service;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.User;
import com.ganbro.domain.entity.UserInfo;

public interface UserService {
    PageData<UserInfo> searchUserInfoByPage(String query, int pageNum, int pageSize);

    void deleteUserById(Integer userId);

    void updateUserInfo(UserInfo userInfo);

    void addUserInfo(UserInfo userInfo);

    UserInfo selectUserInfo(String username);

    void updateDueBooks(String username);
}
