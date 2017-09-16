package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	private Connection conn = null;
	private String uName = "root";
	private String pass = "";
	
	public DbConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hotelReservation",uName,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnetion() {
		return conn;
	}
}
