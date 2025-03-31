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

@WebServlet("/manageCourse")
public class ManageCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CourseDao courseDao = new CourseDao();

        if ("add".equals(action)) {
            String courseName = req.getParameter("courseName");
            String courseCode = req.getParameter("courseCode");
            String description = req.getParameter("description");

            Course course = new Course();
            course.setCourseName(courseName);
            course.setCourseCode(courseCode);
            course.setDescription(description);

            courseDao.addCourse(course);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            courseDao.deleteCourse(id);
        }

        resp.sendRedirect("manageCourse.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CourseDao courseDao = new CourseDao();
        List<Course> courses = courseDao.getAllCourses();
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("manageCourse.jsp").forward(req, resp);
    }
}
