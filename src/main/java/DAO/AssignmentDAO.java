package DAO;
import model.Assignment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDAO {
    private Connection con;

    public AssignmentDAO() throws ClassNotFoundException, SQLException {
        con = DBConnection.getConnection();
    }

    // Save Assignment
    public boolean saveAssignment(Assignment assignment) {
        String query = "INSERT INTO assignments (faculty_id, course_id, title, description, filename, filepath, upload_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, assignment.getFacultyId());
            ps.setInt(2, assignment.getCourseId());
            ps.setString(3, assignment.getTitle());
            ps.setString(4, assignment.getDescription());
            ps.setString(5, assignment.getFilename());
            ps.setString(6, assignment.getFilepath());
            ps.setTimestamp(7, assignment.getUploadDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Fetch Assignments with Faculty Info
    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT a.*, u.name AS faculty_name FROM assignments a JOIN users u ON a.faculty_id = u.id";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(rs.getInt("id"));
                assignment.setTitle(rs.getString("title"));
                assignment.setDescription(rs.getString("description"));
                assignment.setFilename(rs.getString("filename"));
                assignment.setFilepath(rs.getString("filepath"));
                assignment.setUploadDate(rs.getTimestamp("upload_date"));
                assignment.setFacultyName(rs.getString("faculty_name"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
}
