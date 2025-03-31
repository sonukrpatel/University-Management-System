package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {

    public StudentDetails getStudentDetails(int userId) {
        StudentDetails student = null;

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM student_details WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new StudentDetails();
                student.setFullName(rs.getString("full_name"));
                student.setFatherName(rs.getString("fathers_name"));
                student.setAddress(rs.getString("address"));
                student.setPhoneNumber(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
                student.setAadharCard(rs.getString("aadhar_card"));
                student.setPanCard(rs.getString("pan_card"));
                student.setCourse(rs.getString("course"));
                student.setDepartment(rs.getString("department"));
            }
            

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }
}
