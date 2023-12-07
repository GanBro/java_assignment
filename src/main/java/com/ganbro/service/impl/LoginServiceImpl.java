package com.ganbro.service.impl;

import com.ganbro.domain.entity.User;
import com.ganbro.mapper.LoginMapper;
import com.ganbro.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LoginMapper loginMapper;

    @Override
    public String findPasswordByUsername(String username) {
        return loginMapper.findPasswordByUsername(username);
    }

    @Override
    public int findIsAdminByUsername(String username) {
        return loginMapper.findIsAdminByUsername(username);
    }
}
