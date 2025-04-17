<%@ page import="java.util.List, DAO.Course, java.util.ArrayList" %>
<%
    List<Course> courses = (List<Course>) request.getAttribute("courses");

    if (courses == null) {
        courses = new ArrayList<>(); // Prevent NullPointerException
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Courses</title>
     <link rel="stylesheet" href="viewCourses.css">
</head>
<body>

    <h2>Available Courses</h2>

    <% if (courses.isEmpty()) { %>
        <p>No courses available.</p>
    <% } else { %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Course Name</th>
                <th>Course Code</th>
                <th>Description</th>
            </tr>
            <% for (Course course : courses) { %>
            <tr>
                <td><%= course.getId() %></td>
                <td><%= course.getCourseName() %></td>
                <td><%= course.getCourseCode() %></td>
                <td><%= course.getDescription() %></td>
            </tr>
            <% } %>
        </table>
    <% } %>

</body>
</html>
