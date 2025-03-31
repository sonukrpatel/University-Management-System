package Controller;
import DAO.AssignmentDAO;
import model.Assignment;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadAssignment")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 10, 
                 maxRequestSize = 1024 * 1024 * 50)
public class UploadAssignmentServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "assignments";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String facultyIdStr = request.getParameter("facultyId");
        String courseIdStr = request.getParameter("courseId");
        
        // Check if values are null or empty
        if (facultyIdStr == null || facultyIdStr.isEmpty() || courseIdStr == null || courseIdStr.isEmpty()) {
            response.sendRedirect("facultyDashboard.jsp?upload=error");
            return;
        }

        int facultyId = Integer.parseInt(facultyIdStr);
        int courseId = Integer.parseInt(courseIdStr);
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Part filePart = request.getPart("assignmentFile");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "assignments";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        Assignment assignment = new Assignment();
        assignment.setFacultyId(facultyId);
        assignment.setCourseId(courseId);
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setFilename(fileName);
        assignment.setFilepath(filePath);
        assignment.setUploadDate(new Timestamp(System.currentTimeMillis()));

        AssignmentDAO dao = null;
		try {
			dao = new AssignmentDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (dao.saveAssignment(assignment)) {
            response.sendRedirect("facultyDashboard.jsp?upload=success");
        } else {
            response.sendRedirect("facultyDashboard.jsp?upload=failure");
        }
    

    }
}
