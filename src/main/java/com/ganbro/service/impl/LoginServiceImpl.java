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
    public boolean findByUsernameAndPassword(String username, String password) {
        User user = loginMapper.findByUsernameAndPassword(username, password);
        return !(user == null);
    }
}
