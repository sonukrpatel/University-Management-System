<%@ page import="DAO.User" %>
<%
    User user = (User) session.getAttribute("User");
    if (user == null || !user.getRole().equalsIgnoreCase("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>

    <!-- Navigation Bar -->
    <nav>
        <h2>Admin Panel</h2>
        <ul>
            <li><a href="manageUsers.jsp">Manage Users</a></li>
            <li><a href="manageCourse.jsp">Manage Courses</a></li>
            <li><a href="reports.jsp">View Reports</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Welcome Section -->
    <div class="welcome">
        <h1>Welcome, <%= user.getName() %>!</h1>
        <p>Admin Dashboard</p>
    </div>

</body>
</html>
