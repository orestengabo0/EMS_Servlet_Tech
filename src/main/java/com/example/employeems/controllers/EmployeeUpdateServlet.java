package com.example.employeems.controllers;

import com.example.employeems.models.Employee;
import com.example.employeems.services.EmployeeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class EmployeeUpdateServlet extends HttpServlet {
    private EmployeeService employeeService;

    // Do Get for Displaying Employee List
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteEmployee(request, response);
        } else if ("edit".equals(action)) {
            editEmployee(request, response);
        } else {
            listEmployees(request, response);
        }
    }

    // List all employees
    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeService.getAllEmployees());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/employee/list.jsp");
        dispatcher.forward(request, response);
    }

    // Delete an employee
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        employeeService.deleteEmployee(employeeId);
        response.sendRedirect(request.getContextPath() + "/employee");
    }

    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("id"));
        Employee employee = employeeService.getEmployeeById(employeeId);
        request.setAttribute("employee", employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath()+"/employee/update");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateEmployee(request, response);
        }
    }

    // Update employee information
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("fName");
        String lastName = request.getParameter("lName");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        int salary = Integer.parseInt(request.getParameter("salary"));

        // Check if position is null or empty, and set a default value if necessary
        if (position == null || position.trim().isEmpty()) {
            position = "Unknown";  // Set a default value or handle accordingly
        }

        // Create the Employee object
        Employee employee = new Employee(id, firstName, lastName, position, department, salary);

        // Call the service to update the employee
        employeeService.updateEmployee(employee);

        // Redirect to the employee list page
        response.sendRedirect(request.getContextPath() + "/employee/list");
    }
}
