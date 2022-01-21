package com.example.tema3.service;

import com.example.tema3.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}