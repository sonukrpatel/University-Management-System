package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public User getByEmail(String email) {
        User user = null;
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    
 // ✅ Method to fetch all users (Newly added)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM users";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPass(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public boolean updateUser(User user) {
        boolean updated = false;
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE users SET name=?, email=?, role=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole());
            ps.setInt(4, user.getId());

            int rowsAffected = ps.executeUpdate();
            updated = rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }
    
    
    public boolean deleteUser(int id) {
        boolean deleted = false;
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM users WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            deleted = rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }
    
    
    public User getUserById(int id) {
        User user = null;
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    
    
    
    
}
