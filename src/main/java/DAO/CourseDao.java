package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DAO.Course;

public class CourseDao {
    
    public boolean addCourse(Course course) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO courses (course_name, course_code, description) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseCode());
            ps.setString(3, course.getDescription());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM courses";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setCourseCode(rs.getString("course_code"));
                course.setDescription(rs.getString("description"));
                courses.add(course);
               
            }
            System.out.println("Total courses fetched: " + courses.size()); // Debugging line
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (courses.isEmpty()) {
            System.out.println("Course list is empty!");  // Debugging
        }
        
        return courses;
    }


    public boolean deleteCourse(int id) {
        try {
            Connection con = DBConnection.getConnection();
            String query = "DELETE FROM courses WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
