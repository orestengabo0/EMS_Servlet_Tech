<%@ page import="com.example.employeems.models.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
    <title>Add Employee</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Add New Employee</h2>

    <div class="card shadow-lg p-4">
        <form action="<%= request.getContextPath() %>/employee/create" method="post">
            <div class="mb-3">
                <label for="fName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="fName" name="fName" required>
            </div>

            <div class="mb-3">
                <label for="lName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lName" name="lName" required>
            </div>

            <div class="mb-3">
                <label for="pos" class="form-label">Position</label>
                <input type="text" class="form-control" id="pos" name="pos" required>
            </div>

            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department" required>
            </div>

            <div class="mb-3">
                <label for="salary" class="form-label">Salary</label>
                <input type="number" class="form-control" id="salary" name="salary" required>
            </div>

            <button type="submit" class="btn btn-primary w-100">Add Employee</button>
        </form>
    </div>

    <div class="text-center mt-3">
        <a href="<%= request.getContextPath() %>/employee/list" class="btn btn-secondary">View Employees</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
