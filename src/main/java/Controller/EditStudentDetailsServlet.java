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

@WebServlet("/EditStudentDetailsServlet")
public class EditStudentDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("user_id"));
        String fullName = request.getParameter("full_name");
        String fathersName = request.getParameter("fathers_name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String aadharCard = request.getParameter("aadhar_card");
        String panCard = request.getParameter("pan_card");
        String course = request.getParameter("course");
        String department = request.getParameter("department");
        String otherDetails = request.getParameter("other_details");

        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE student_details SET full_name=?, fathers_name=?, address=?, phone=?, email=?, aadhar_card=?, pan_card=?, course=?, department=?, other_details=? WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, fullName);
            ps.setString(2, fathersName);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, aadharCard);
            ps.setString(7, panCard);
            ps.setString(8, course);
            ps.setString(9, department);
            ps.setString(10, otherDetails);
            ps.setInt(11, userId);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.sendRedirect("studentDashboard.jsp?message=Profile updated successfully");
            } else {
                response.getWriter().println("Error updating profile.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}

