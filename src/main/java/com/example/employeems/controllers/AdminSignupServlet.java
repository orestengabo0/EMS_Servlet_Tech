package com.example.employeems.controllers;

import com.example.employeems.models.User;
import com.example.employeems.serviceImpl.UserServiceImpl;
import com.example.employeems.services.UserService;
import com.example.employeems.utility.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminSignupServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Direct to signup page (if using JSP for form rendering)
        request.getRequestDispatcher("/adminSignup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Hash the password (consider using bcrypt or another hashing algorithm)
        String hashedPassword = hashPassword(password);

        // Create a new admin user
        User admin = new User(0, username, hashedPassword, email, "admin");

        // Register the admin
        userService.registerAdmin(admin);
        response.sendRedirect(request.getContextPath()+"/admin/login");
    }

    private String hashPassword(String password) {
        // Implement password hashing (e.g., using BCrypt)
        return PasswordUtil.hashPassword(password);
    }
}
