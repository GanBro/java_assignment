package com.ganbro.service;

import com.ganbro.domain.common.PageData;
import com.ganbro.domain.entity.User;

public interface UserService {
    PageData<User> searchUsersByPage(String query, int pageNum, int pageSize);
}
