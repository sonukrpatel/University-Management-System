<%@ page import="java.util.List, DAO.StudentDao, DAO.CourseDao,  DAO.User, DAO.Course" %>
<%@ page import="java.util.ArrayList" %>
<%
    // Assuming faculty is logged in and has a valid session
    User faculty = (User) session.getAttribute("User");
    if (faculty == null || !faculty.getRole().equalsIgnoreCase("faculty")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grade Student</title>
    <link rel="stylesheet" href="dashboard.css">
    <link rel="stylesheet" href="gradeStudent.css">
</head>
<body>
    <nav>
        <h2>Faculty Panel</h2>
        <ul>
            <li><a href="facultyDashboard.jsp">Dashboard</a></li>
            <li><a href="gradeStudent.jsp">Grade Student</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <div class="container">
        <h2>Grade Student</h2>
        <!-- Form to assign/update grade -->
        <form action="GradeStudentServlet" method="post">
            <label>Student ID:</label>
            <input type="text" name="student_id" placeholder="Enter Student ID" required>
            
            <label>Course ID:</label>
            <input type="text" name="course_id" placeholder="Enter Course ID" required>
            
            <label>Grade:</label>
            <input type="text" name="grade" placeholder="e.g., A, B+, etc." required>
            
            <label>Comments:</label>
            <textarea name="comments" placeholder="Optional comments"></textarea>
            
            <button type="submit">Submit Grade</button>
        </form>
    </div>
</body>
</html>
