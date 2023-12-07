package com.ganbro.service;

public interface LoginService {

    String findPasswordByUsername(String username);

    int findIsAdminByUsername(String username);
}
