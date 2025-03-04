<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.employeems.models.Employee" %>
<%@ page import="com.example.employeems.models.User" %>

<%
    // Check if the user is logged in, if not redirect to the login page
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee List</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%= request.getContextPath() %>/employee/list">EmployeeMS</a>

        <div class="collapse navbar-collapse justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="profileDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <%= loggedInUser.getUsername() %>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="profileDropdown">
                        <li><a class="dropdown-item" href="#">View Profile</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item text-danger" href="<%= request.getContextPath() %>/admin/logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center mb-4">Employee List</h2>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Position</th>
            <th>Department</th>
            <th>Salary</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Employee> employees = (List<Employee>) request.getAttribute("employees");
            if (employees != null) {
                for (Employee emp : employees) {
        %>
        <tr>
            <td><%= emp.getId() %></td>
            <td><%= emp.getFirstName() + " " + emp.getLastName() %></td>
            <td><%= emp.getPosition() %></td>
            <td><%= emp.getDepartment() %></td>
            <td><%= emp.getSalary() %></td>
            <td>
                <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#editModal"
                        data-id="<%= emp.getId() %>"
                        data-firstname="<%= emp.getFirstName() %>"
                        data-lastname="<%= emp.getLastName() %>"
                        data-position="<%= emp.getPosition() %>"
                        data-department="<%= emp.getDepartment() %>"
                        data-salary="<%= emp.getSalary() %>">
                    Edit
                </button>
                <a href="<%= request.getContextPath() %>/employee/delete?id=<%= emp.getId() %>" class="btn btn-danger"
                   onclick="return confirm('Are you sure you want to delete this employee?')">Delete</a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="6" class="text-center">No employees found.</td></tr>
        <% } %>
        </tbody>
    </table>

    <div class="text-center mt-3">
        <a href="<%= request.getContextPath() %>/employee/create" class="btn btn-primary">Add New Employee</a>
    </div>
</div>

<!-- Edit Employee Modal -->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit Employee</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editEmployeeForm" method="POST" action="<%= request.getContextPath() %>/employee/update">
                    <input type="hidden" id="employeeId" name="id">
                    <div class="mb-3">
                        <label for="fName" class="form-label">First Name</label>
                        <input type="text" class="form-control" id="fName" name="fName" required>
                    </div>
                    <div class="mb-3">
                        <label for="lName" class="form-label">Last Name</label>
                        <input type="text" class="form-control" id="lName" name="lName" required>
                    </div>
                    <div class="mb-3">
                        <label for="position" class="form-label">Position</label>
                        <input type="text" class="form-control" id="position" name="position" required>
                    </div>
                    <div class="mb-3">
                        <label for="department" class="form-label">Department</label>
                        <input type="text" class="form-control" id="department" name="department" required>
                    </div>
                    <div class="mb-3">
                        <label for="salary" class="form-label">Salary</label>
                        <input type="number" class="form-control" id="salary" name="salary" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // JavaScript to populate the modal with the employee data when "Edit" is clicked
    document.addEventListener('DOMContentLoaded', function() {
        var editButtons = document.querySelectorAll('.btn-warning');
        editButtons.forEach(function(button) {
            button.addEventListener('click', function() {
                document.getElementById('employeeId').value = button.getAttribute('data-id');
                document.getElementById('fName').value = button.getAttribute('data-firstname');
                document.getElementById('lName').value = button.getAttribute('data-lastname');
                document.getElementById('position').value = button.getAttribute('data-position');
                document.getElementById('department').value = button.getAttribute('data-department');
                document.getElementById('salary').value = button.getAttribute('data-salary');
            });
        });
    });
</script>

</body>
</html>
