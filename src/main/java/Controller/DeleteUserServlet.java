package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UserDao;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        UserDao userDao = new UserDao();
        if (userDao.deleteUser(userId)) {
            response.sendRedirect("manageUsers.jsp");
        } else {
            response.getWriter().println("Error deleting user.");
        }
    }
}
