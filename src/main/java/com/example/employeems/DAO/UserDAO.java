package com.example.employeems.DAO;

import com.example.employeems.models.User;

public interface UserDAO {
    void addAdmin(User admin);
    User getAdminByUsernameAndPassword(String username, String password);
}
