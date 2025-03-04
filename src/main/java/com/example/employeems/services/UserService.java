package com.example.employeems.services;

import com.example.employeems.models.User;

public interface UserService {
    void registerAdmin(User admin);
    User loginAdmin(String username, String password);
}
