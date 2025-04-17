<%@ page import="DAO.User" %>
<%
    User user = (User) session.getAttribute("User");
    if (user == null || !user.getRole().equalsIgnoreCase("student")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>

    <!-- Navigation Bar -->
    <nav>
        <h2>Student Panel</h2>
        <ul>
        	<li><a href="studentDetails.jsp">Add Details</a></li>
            <li><a href="myCourses.jsp">My Courses</a></li>
            <li><a href="downloadAssignments.jsp">Assignments</a></li>
            <li><a href="myGrade.jsp">My Grades</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <!-- Welcome Section -->
    <div class="welcome">
        <h1>Welcome, <%= user.getName() %>!</h1>
        <p>Student Dashboard</p>
        
        <!-- View Profile Button -->
        <form action="viewStudentDetails.jsp" method="get">
            <button type="submit" class="profile-btn">View Profile</button>
        </form>
    </div>

</body>
</html>
