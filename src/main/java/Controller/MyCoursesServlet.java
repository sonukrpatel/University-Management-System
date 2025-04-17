package Controller;

import DAO.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/myCourses")
public class MyCoursesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer studentId = (Integer) session.getAttribute("userId");

        if (studentId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<String[]> courseList = new ArrayList<>();

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT c.course_name, c.course_code, c.description " +
                           "FROM enrollment e JOIN courses c ON e.course_id = c.id " +
                           "WHERE e.student_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] course = new String[3];
                course[0] = rs.getString("course_name");
                course[1] = rs.getString("course_code");
                course[2] = rs.getString("description");
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("courseList", courseList);
        request.getRequestDispatcher("myCourses.jsp").forward(request, response);
    }
}
