<%@ page import="java.util.List, model.Course, DAO.CourseDao" %>




<%
    CourseDao courseDao = new CourseDao();
    List<Course> courses = courseDao.getAllCourses();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Courses</title>
    <link rel="stylesheet" href="manageCourse.css">
</head>
<body>

    <div class="container">
        <h2>Manage Courses</h2>
        <form action="manageCourse" method="post">
            <input type="hidden" name="action" value="add">
            <input type="text" name="courseName" placeholder="Course Name" required>
            <input type="text" name="courseCode" placeholder="Course Code" required>
            <textarea name="description" placeholder="Course Description"></textarea>
            <button type="submit">Add Course</button>
        </form>

        <h3>All Courses</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Course Name</th>
                <th>Course Code</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            <% for (Course course : courses) { %>
            <tr>
                <td><%= course.getId() %></td>
                <td><%= course.getCourseName() %></td>
                <td><%= course.getCourseCode() %></td>
                <td><%= course.getDescription() %></td>
                <td>
                    <form action="manageCourse" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= course.getId() %>">
                        <button type="submit" class="btn-delete">Delete</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    </div>

</body>
</html>
