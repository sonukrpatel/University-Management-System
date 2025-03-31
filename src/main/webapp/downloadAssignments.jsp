<%@ page import="java.sql.*" %>
<%@ page import="DAO.DBConnection" %>

<!DOCTYPE html>
<html>
<head>
    <title>Download Assignments</title>
    <link rel="stylesheet" href="downloadAssignment.css">
</head>
<body>
   

    <h2>Available Assignments</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Uploaded By</th>
            <th>Upload Date</th>
            <th>Download</th>
        </tr>

        <%
            try (Connection con = DBConnection.getConnection()) {
                String query = "SELECT a.id, a.title, a.description, u.name AS faculty_name, a.upload_date " +
                               "FROM assignments a JOIN users u ON a.faculty_id = u.id";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("title") %></td>
            <td><%= rs.getString("description") %></td>
            <td><%= rs.getString("faculty_name") %></td>
            <td><%= rs.getTimestamp("upload_date") %></td>
            <td><a href="downloadAssignment?id=<%= rs.getInt("id") %>">Download</a></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<tr><td colspan='6'>Error fetching assignments.</td></tr>");
            }
        %>
    </table>
</body>
</html>
