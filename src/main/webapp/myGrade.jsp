<%@ page import="java.sql.*, DAO.DBConnection" %>
<%@ page import="java.util.ArrayList, java.util.List" %>
<%
    // Ensure the student is logged in
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    List<String[]> gradesList = new ArrayList<>();
    
    try (Connection con = DBConnection.getConnection()) {
        // Fetch grades for the logged-in student
        String query = "SELECT g.grade, g.comments, c.course_name " +
                       "FROM grades g JOIN courses c ON g.course_id = c.id " +
                       "WHERE g.student_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            // For simplicity, we create an array: [course, grade, comments]
            String[] record = new String[3];
            record[0] = rs.getString("course_name");
            record[1] = rs.getString("grade");
            record[2] = rs.getString("comments");
            gradesList.add(record);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Grades</title>
    <link rel="stylesheet" href="myGrade.css">
</head>
<body>
    <nav>
        <h2>Student Panel</h2>
        <ul>
            <li><a href="studentDashboard.jsp">Dashboard</a></li>
            <li><a href="myGrade.jsp">My Grades</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <div class="container">
        <h2>My Grades</h2>
        <% if (gradesList.isEmpty()) { %>
            <p>No grades available yet.</p>
        <% } else { %>
            <table>
                <tr>
                    <th>Course</th>
                    <th>Grade</th>
                    <th>Comments</th>
                </tr>
                <% for (String[] record : gradesList) { %>
                    <tr>
                        <td><%= record[0] %></td>
                        <td><%= record[1] %></td>
                        <td><%= record[2] %></td>
                    </tr>
                <% } %>
            </table>
        <% } %>
    </div>
</body>
</html>
