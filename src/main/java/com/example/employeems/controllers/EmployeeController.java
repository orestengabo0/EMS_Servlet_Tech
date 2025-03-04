package com.example.employeems.controllers;

import com.example.employeems.models.Employee;
import com.example.employeems.serviceImpl.EmployeeServiceImpl;
import com.example.employeems.services.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class EmployeeController extends HttpServlet {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employee> employees = employeeService.getAllEmployees();
        request.setAttribute("employee", employees);
        request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("fName");
        String lastName = request.getParameter("lName");
        String position = request.getParameter("pos");
        String department = request.getParameter("department");
        int salary = Integer.parseInt(request.getParameter("salary"));

        Employee employee = new Employee(0, firstName, lastName, position, department, salary);
        employeeService.addEmployee(employee);
        response.sendRedirect(request.getContextPath() + "/employee/list");
    }

}
