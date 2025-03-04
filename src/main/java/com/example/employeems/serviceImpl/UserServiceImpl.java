package com.example.employeems.serviceImpl;

import com.example.employeems.DAO.UserDAO;
import com.example.employeems.DAOImpl.UserDAOImpl;
import com.example.employeems.models.User;
import com.example.employeems.services.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void registerAdmin(User admin) {
         userDAO.addAdmin(admin);
    }

    @Override
    public User loginAdmin(String username, String password) {
        return userDAO.getAdminByUsernameAndPassword(username, password);
    }
}
