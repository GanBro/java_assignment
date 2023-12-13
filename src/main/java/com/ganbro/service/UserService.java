package com.ganbro.service;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.common.ReturnModel;
import com.ganbro.domain.entity.UserInfo;

public interface UserService {
    PageData<UserInfo> searchUserInfoByPage(String query, int pageNum, int pageSize);

    ReturnModel deleteUserById(Integer userId);

    ReturnModel updateUserInfo(UserInfo userInfo);

    ReturnModel addUserInfo(UserInfo userInfo);

    UserInfo selectUserInfo(String username);

    void updateDueBooks(String username);

    void resetDueBooks(String username);
}
