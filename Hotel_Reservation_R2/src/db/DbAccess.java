package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbAccess {
	
	private Connection conn = null;
	private PreparedStatement prepStatement = null;
	private ResultSet rs = null;
	
	public DbAccess(){
		conn = new DbConnect().getConnetion();
	}
	
	public boolean isValidLogin(String uName, String password) {
		
		boolean isValid=false;
		String SQLUserLogin = "SELECT COUNT(*) as matchCount "
							+ "FROM employee "
							+ "WHERE uname=? "
								+ "AND password=?";
		
		try {
			prepStatement = conn.prepareStatement(SQLUserLogin);
			prepStatement.setString(1, uName);
			prepStatement.setString(2, password);	
			
			rs=prepStatement.executeQuery();
			
			if(!rs.equals(null)){
				rs.next();
				if(rs.getInt("matchCount")==1){
					isValid=true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isValid;
	}
	
	public ResultSet getAllEmployees(String searchStr){
		
		String SQLUserLogin = "SELECT * "
						+ "FROM employee ";	
		
		if(!searchStr.equals("")){
			SQLUserLogin += "WHERE fname LIKE ? "
								+ "OR lname LIKE ? "
								+ "OR uname LIKE ? ";
		}
		try {
			prepStatement = conn.prepareStatement(SQLUserLogin);
			
			if(!searchStr.equals("")){
				prepStatement.setString(1, "%"+searchStr+"%");
				prepStatement.setString(2, "%"+searchStr+"%");
				prepStatement.setString(3, "%"+searchStr+"%");
			}			
			
			rs=prepStatement.executeQuery();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
