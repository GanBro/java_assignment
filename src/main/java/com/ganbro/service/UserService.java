package com.ganbro.service;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.ReturnModel;
import com.ganbro.domain.dto.DeleteUserInfoDto;
import com.ganbro.domain.entity.BookInfo;
import com.ganbro.domain.entity.User;
import com.ganbro.domain.entity.UserInfo;

public interface UserService {
    PageData<UserInfo> searchUserInfoByPage(String query, int pageNum, int pageSize);

    DeleteUserInfoDto deleteUserById(Integer userId);

    ReturnModel updateUserInfo(UserInfo userInfo);

    void addUserInfo(UserInfo userInfo);

    UserInfo selectUserInfo(String username);

    void updateDueBooks(String username);
}
