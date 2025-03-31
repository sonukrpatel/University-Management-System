<%@ page import="DAO.User" %>
<%
    User user = (User) session.getAttribute("User");
    if (user == null || !user.getRole().equalsIgnoreCase("faculty")) {
        response.sendRedirect("login.jsp");
        return;
    }
    int facultyId = user.getId(); // Get the user ID
%>

<!DOCTYPE html>
<html>
<head>
    <title>Upload Assignment</title>
    <link rel="stylesheet" href="uploadAssignment.css">
</head>
<body>

    <h2>Upload Assignment</h2>
    <form action="uploadAssignment" method="post" enctype="multipart/form-data">
   	    <input type="hidden" name="facultyId" value="<%= facultyId %>">
        <label>Course:</label>
        <select name="courseId" required>
            <option value="1">Course 1</option>
            <option value="2">Course 2</option>
        </select><br><br>

        <input type="text" name="title" placeholder="Assignment Title" required><br>
        <textarea name="description" placeholder="Assignment Description"></textarea><br>
        <input type="file" name="assignmentFile" required><br>
        <button type="submit">Upload Assignment</button>
    </form>

</body>
</html>
