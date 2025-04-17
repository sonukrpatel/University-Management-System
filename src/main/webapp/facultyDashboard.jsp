<%@ page import="DAO.User" %>
<%
    User user = (User) session.getAttribute("User");
    if (user == null || !user.getRole().equalsIgnoreCase("faculty")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Faculty Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>

    <!-- Navigation Bar -->
    <nav>
        <h2>Faculty Panel</h2>
        <ul>
            <li><a href="viewCourses">View Courses</a></li>
            <li><a href="uploadAssignment.jsp">Upload Assignments</a></li>
            <li><a href="gradeStudent.jsp">Grade Students</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Welcome Section -->
    <div class="welcome">
        <h1>Welcome, <%= user.getName() %>!</h1>
        <p>Faculty Dashboard</p>
    </div>

</body>
</html>
