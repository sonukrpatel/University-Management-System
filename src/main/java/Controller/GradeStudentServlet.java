package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DBConnection;

@WebServlet("/GradeStudentServlet")
public class GradeStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        int courseId = Integer.parseInt(request.getParameter("course_id"));
        String grade = request.getParameter("grade");
        String comments = request.getParameter("comments");

        try (Connection con = DBConnection.getConnection()) {
            // Check if grade record already exists for this student and course
            String checkQuery = "SELECT id FROM grades WHERE student_id=? AND course_id=?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, studentId);
            checkStmt.setInt(2, courseId);
            if (checkStmt.executeQuery().next()) {
                // Update existing record
                String updateQuery = "UPDATE grades SET grade=?, comments=? WHERE student_id=? AND course_id=?";
                PreparedStatement updateStmt = con.prepareStatement(updateQuery);
                updateStmt.setString(1, grade);
                updateStmt.setString(2, comments);
                updateStmt.setInt(3, studentId);
                updateStmt.setInt(4, courseId);
                updateStmt.executeUpdate();
            } else {
                // Insert new grade record
                String insertQuery = "INSERT INTO grades (student_id, course_id, grade, comments) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                insertStmt.setInt(1, studentId);
                insertStmt.setInt(2, courseId);
                insertStmt.setString(3, grade);
                insertStmt.setString(4, comments);
                insertStmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error processing grade: " + e.getMessage());
            return;
        }
        response.sendRedirect("facultyDashboard.jsp?message=Grade processed successfully");
    }
}
