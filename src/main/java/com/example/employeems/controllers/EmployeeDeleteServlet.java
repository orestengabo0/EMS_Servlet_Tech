package com.example.employeems.controllers;

import com.example.employeems.serviceImpl.EmployeeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EmployeeDeleteServlet extends HttpServlet {
    private EmployeeServiceImpl employeeService;

    public EmployeeDeleteServlet() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDelete(request, response); // Forward GET requests to DELETE for debugging
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);

                // Call the service to delete the employee
                employeeService.deleteEmployee(id);

                // Redirect to the employee list page after successful deletion
                response.sendRedirect(request.getContextPath() + "/employee/list");
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Invalid employee ID.");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Employee ID is required.");
        }
    }

}
