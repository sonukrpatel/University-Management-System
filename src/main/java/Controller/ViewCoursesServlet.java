package Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.CourseDao;
import DAO.Course;

@WebServlet("/viewCourses")
public class ViewCoursesServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseDao courseDao = new CourseDao();
        List<Course> courses = courseDao.getAllCourses(); // Fetch courses from DB

        if (courses == null) {  // Debugging log
            System.out.println("No courses found!");
        }

        req.setAttribute("courses", courses);
        req.getRequestDispatcher("viewCourses.jsp").forward(req, resp);
    }
}
