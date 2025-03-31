package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DBConnection;

@WebServlet("/downloadAssignment")
public class DownloadAssignmentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Step 1: Retrieve Assignment ID
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.getWriter().println("Invalid assignment ID.");
            return;
        }
        
        int assignmentId;
        try {
            assignmentId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.getWriter().println("Assignment ID must be a number.");
            return;
        }

        // Step 2: Database Query
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT filename, filepath FROM assignments WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, assignmentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String fileName = rs.getString("filename");
                String filePath = rs.getString("filepath");

                // Step 3: Print debugging information
                System.out.println("Downloading file: " + fileName);
                System.out.println("File path: " + filePath);

                // Step 4: Check if file exists
                File file = new File(filePath);
                if (!file.exists()) {
                    response.getWriter().println("Error: File does not exist on server.");
                    return;
                }

                // Step 5: Send File as Response
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                FileInputStream fileInputStream = new FileInputStream(file);
                OutputStream outStream = response.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                fileInputStream.close();
                outStream.close();
            } else {
                response.getWriter().println("Error: Assignment not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error retrieving file: " + e.getMessage());
        }
    }
}
