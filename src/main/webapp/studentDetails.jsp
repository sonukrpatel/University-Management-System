
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
    <title>Student Details</title>
    <link rel="stylesheet" href="studentDetails.css">
</head>
<body>

    <h2>Enter Your Details</h2>
    <form action="StudentDetailsServlet" method="post">
    <input type="hidden" name="user_id" value="<%= user.getId() %>">
    
    <label>Full Name:</label>
    <input type="text" name="full_name" required><br>

    <label>Father's Name:</label>
    <input type="text" name="fathers_name" required><br>

    <label>Address:</label>
    <input type="text" name="address" required><br>

    <label>Phone:</label>
    <input type="text" name="phone" required><br>

    <label>Email:</label>
    <input type="email" name="email" required><br>

    <label>Aadhar Card:</label>
    <input type="text" name="aadhar_card" required><br>

    <label>PAN Card:</label>
    <input type="text" name="pan_card" required><br>

    <label>Course:</label>
    <input type="text" name="course" required><br>

    <label>Department:</label>
    <input type="text" name="department" required><br>

    <label>Other Details:</label>
    <textarea name="other_details"></textarea><br>

    <button type="submit">Save Details</button>
</form>


</body>
</html>
