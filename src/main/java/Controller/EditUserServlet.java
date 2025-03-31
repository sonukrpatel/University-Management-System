package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UserDao;
import DAO.User;

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        User user = new User(id, name, email, role);
        UserDao userDao = new UserDao();
        
        if (userDao.updateUser(user)) {
            response.sendRedirect("manageUsers.jsp");
        } else {
            response.getWriter().println("Error updating user.");
        }
    }
}
