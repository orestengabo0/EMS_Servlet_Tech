<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String language = request.getParameter("lang");
    if (language == null) {
        language = "en";
    }
    Locale locale = new Locale(language);
    ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
%>

<html>
<head>
    <title><%= bundle.getString("login.title") %></title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .login-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        .language-select-container {
            display: flex;
            justify-content: flex-end; /* Align to the right */
            margin-top: -30px; /* To position it above the form */
        }
        .form-select {
            width: 150px; /* Reduce the width of the select dropdown */
        }
    </style>
</head>
<body>

<div class="container">
    <div class="login-container">
        <!-- Language Selection Form -->
        <div class="language-select-container">
            <form method="GET" class="mt-3">
                <label for="lang" class="me-2"><%= bundle.getString("language.label") %></label>
                <select name="lang" id="lang" class="form-select" onchange="this.form.submit()">
                    <option value="en" <%= "en".equals(language) ? "selected" : "" %>>English</option>
                    <option value="fr" <%= "fr".equals(language) ? "selected" : "" %>>Français</option>
                    <option value="kiny" <%= "kiny".equals(language) ? "selected" : "" %>>Kinyarwanda</option>
                </select>
            </form>
        </div>

        <!-- Login Form -->
        <form action="<%= request.getContextPath() %>/admin/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label"><%= bundle.getString("login.username") %></label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label"><%= bundle.getString("login.password") %></label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100"><%= bundle.getString("login.button") %></button>
        </form>

        <!-- Navigation Links -->
        <div class="text-center mt-3">
            <p>Have no admin account? <a href="<%= request.getContextPath() %>/admin/signup">Sign Up</a></p>
        </div>
    </div>
</div>

<!-- Bootstrap JS (optional for interactive components) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
