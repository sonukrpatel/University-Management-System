package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String USER = "root";  // Change to your DB user name
    private static final String PASSWORD = "root"; // Change to your DB password
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	
    	 Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
