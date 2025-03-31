package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBConnection;


@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role);
            
            int result = ps.executeUpdate();
            if (result > 0) {
                resp.sendRedirect("login.jsp");
            } else {
                out.println("<p style='color:red;'>Signup Failed! Try again.</p>");
                req.getRequestDispatcher("signup.jsp").include(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
