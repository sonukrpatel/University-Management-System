<%@ page import="DAO.UserDao, DAO.User" %>
<%
    int userId = Integer.parseInt(request.getParameter("id"));
    UserDao userDao = new UserDao();
    User user = userDao.getUserById(userId);
%>


<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="editUser.css">
</head>
<body>

<div class="container">
    <h2>Edit User</h2>
    <form action="EditUserServlet" method="post">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        
        <label>Name:</label>
        <input type="text" name="name" value="<%= user.getName() %>" required>

        <label>Email:</label>
        <input type="email" name="email" value="<%= user.getEmail() %>" required>

        <label>Role:</label>
        <select name="role">
            <option value="admin" <%= user.getRole().equals("admin") ? "selected" : "" %>>Admin</option>
            <option value="faculty" <%= user.getRole().equals("faculty") ? "selected" : "" %>>Faculty</option>
            <option value="student" <%= user.getRole().equals("student") ? "selected" : "" %>>Student</option>
        </select>

        <div class="button-group">
            <button type="submit">Update</button>
            <button type="button" class="cancel-btn" onclick="window.location.href='manageUsers.jsp'">Cancel</button>
        </div>
    </form>
</div>

</body>
</html>
