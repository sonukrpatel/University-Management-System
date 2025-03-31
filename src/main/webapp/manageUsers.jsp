<%@ page import="java.util.List, DAO.UserDao, DAO.User" %>
<%
    UserDao userDao = new UserDao();
    List<User> userList = userDao.getAllUsers();
%>

<%
    User user = (User) session.getAttribute("User");
    if (user == null || !user.getRole().equalsIgnoreCase("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" href="dashboard.css">
    <link rel="stylesheet" href="manageUser.css">
    
</head>
<body>

    <nav>
        <h2>Admin Panel</h2>
        <ul>
            <li><a href="adminDashboard.jsp">Dashboard</a></li>
            <li><a href="manageUsers.jsp">Manage Users</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <h2>Manage Users</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <% for (User u : userList) { %>
        <tr>
            <td><%= u.getId() %></td>
            <td><%= u.getName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRole() %></td>
            <td>
			    <a href="editUser.jsp?id=<%= u.getId() %>">Edit</a> |
			    <a href="deleteUser?id=<%= u.getId() %>" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
			</td>

            
            
        </tr>
        <% } %>
    </table>

</body>
</html>
