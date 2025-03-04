package com.example.employeems.DAOImpl;

import com.example.employeems.DAO.EmployeeDAO;
import com.example.employeems.models.Employee;
import com.example.employeems.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    Connection conn = DBConnection.getConnection();

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (first_name, last_name, position, department, salary) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            // Set the values in the PreparedStatement in the correct order
            preparedStatement.setString(1, employee.getFirstName());  // first_name
            preparedStatement.setString(2, employee.getLastName());   // last_name
            preparedStatement.setString(3, employee.getPosition());   // position
            preparedStatement.setString(4, employee.getDepartment()); // department
            preparedStatement.setInt(5, employee.getSalary());     // salary

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employee was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(); // Correctly using executeQuery() here to fetch data
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("position"),
                        resultSet.getString("department"),
                        resultSet.getInt("salary")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        String sql = "SELECT id, first_name, last_name, position, department, salary FROM employees WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("position"),
                        resultSet.getString("department"),
                        resultSet.getInt("salary")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if employee not found
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, position = ?, department = ?, salary = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setString(4, employee.getDepartment());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
