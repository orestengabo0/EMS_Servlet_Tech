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

public class AdminLoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Direct to login page (if using JSP for form rendering)
        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userService.loginAdmin(username, password);

        if (user != null && user.getRole().equals("admin")) {
            // Set a session attribute or JWT token to track logged-in user
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect(request.getContextPath()+"/employee/list");  // Redirect to admin dashboard
        } else {
            response.sendRedirect(request.getContextPath()+"/admin/login?error=Invalid credentials");
        }
    }
}
