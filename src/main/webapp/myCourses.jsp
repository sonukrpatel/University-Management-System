<%@ page import="java.util.List" %>

<%
    List<String[]> courseList = (List<String[]>) request.getAttribute("courseList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Courses</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { padding: 10px; border: 1px solid black; text-align: center; }
        h2 { text-align: center; }
    </style>
</head>
<body>
    <h2>My Enrolled Courses</h2>
    <% if (courseList == null || courseList.isEmpty()) { %>
        <p style="text-align:center;">No enrolled courses found.</p>
    <% } else { %>
        <table>
            <tr>
                <th>Course Name</th>
                <th>Course Code</th>
                <th>Description</th>
            </tr>
            <% for (String[] course : courseList) { %>
                <tr>
                    <td><%= course[0] %></td>
                    <td><%= course[1] %></td>
                    <td><%= course[2] %></td>
                </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>
