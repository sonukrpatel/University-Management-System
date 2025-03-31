package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.User;
import DAO.UserDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao dao = new UserDao();

        try {
            User u = dao.getByEmail(email);
            if (u != null) { // User exists

                if (u.getPass().equals(password)) { // Password matches
                    HttpSession session = req.getSession();
                    session.setAttribute("User", u);
                    
                    // Redirect based on role
                    if (u.getRole().equalsIgnoreCase("admin")) {
                        resp.sendRedirect("adminDashboard.jsp");
                    } else if (u.getRole().equalsIgnoreCase("faculty")) {
                        resp.sendRedirect("facultyDashboard.jsp");
                    } else {
                        resp.sendRedirect("studentDashboard.jsp");
                    }

                } else { // Wrong password
                    req.setAttribute("error", "Incorrect Password!");
                    RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                    dispatcher.forward(req, resp);
                }

            } else { // User not found
                req.setAttribute("error", "Email not registered!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<p style='color:red'>Something went wrong! Try again.</p>");
        }
    }
}
