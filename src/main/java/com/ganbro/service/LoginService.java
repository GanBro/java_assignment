package com.ganbro.service;

public interface LoginService {
    boolean findByUsernameAndPassword(String username, String password);
}
