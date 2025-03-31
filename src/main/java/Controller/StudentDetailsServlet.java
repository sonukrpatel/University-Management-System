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

@WebServlet("/StudentDetailsServlet")
public class StudentDetailsServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    System.out.println("StudentDetailsServlet called!"); // Debugging Log
	    String userIdStr = request.getParameter("user_id");
	    
	    if (userIdStr == null || userIdStr.isEmpty()) {
	        System.out.println("Error: user_id is null or empty");
	        response.getWriter().println("Error: User ID is missing.");
	        return;
	    }

	    int userId = Integer.parseInt(userIdStr);
	    System.out.println("User ID: " + userId);

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

	    System.out.println("Received Data: " + fullName + ", " + fathersName + ", " + address);

	    try (Connection con = DBConnection.getConnection()) {
	        String query = "INSERT INTO student_details (user_id, full_name, fathers_name, address, phone, email, aadhar_card, pan_card, course, department, other_details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, userId);
	        ps.setString(2, fullName);
	        ps.setString(3, fathersName);
	        ps.setString(4, address);
	        ps.setString(5, phone);
	        ps.setString(6, email);
	        ps.setString(7, aadharCard);
	        ps.setString(8, panCard);
	        ps.setString(9, course);
	        ps.setString(10, department);
	        ps.setString(11, otherDetails);

	        int result = ps.executeUpdate();

	        System.out.println("Insert Query Result: " + result); // Debugging Log

	        if (result > 0) {
	            response.sendRedirect("studentDashboard.jsp?message=Profile saved successfully");
	        } else {
	            response.getWriter().println("Error saving profile.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Error: " + e.getMessage());
	    }
	}

}
