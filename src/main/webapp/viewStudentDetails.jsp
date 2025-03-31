<%@ page import="DAO.StudentDao, DAO.StudentDetails, DAO.User" %>
<%
    // Get logged-in student details from session
    User user = (User) session.getAttribute("User");
    if (user == null || !user.getRole().equalsIgnoreCase("student")) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch student details from database
    StudentDao studentDao = new StudentDao();
    StudentDetails student = studentDao.getStudentDetails(user.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Student Details</title>
    <link rel="stylesheet" href="viewStudentDetails.css">
</head>
<body>

    <nav>
        <h2>Student Panel</h2>
        <ul>
            <li><a href="studentDashboard.jsp">Dashboard</a></li>
            <li><a href="studentDetails.jsp">Edit Profile</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <div class="container">
        <h2>Student Profile</h2>

        <% if (student == null) { %>
            <p class="error">No details found. Please <a href="studentDetails.jsp">add your details</a>.</p>
        <% } else { %>
            <table>
                <tr><th>Full Name:</th><td><%= student.getFullName() %></td></tr>
                <tr><th>Father's Name:</th><td><%= student.getFatherName() %></td></tr>
                <tr><th>Address:</th><td><%= student.getAddress() %></td></tr>
                <tr><th>Phone Number:</th><td><%= student.getPhoneNumber() %></td></tr>
                <tr><th>Email:</th><td><%= student.getEmail() %></td></tr>
                <tr><th>Aadhaar Card:</th><td><%= student.getAadharCard() %></td></tr>
                <tr><th>PAN Card:</th><td><%= student.getPanCard() %></td></tr>
                <tr><th>Course:</th><td><%= student.getCourse() %></td></tr>
                <tr><th>Department:</th><td><%= student.getDepartment() %></td></tr>
            </table>
        <% } %>

        <a href="studentDashboard.jsp" class="btn">Back to Dashboard</a>
    </div>

</body>
</html>
