package com.focuseddeveloper.servedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;

public class FetchUsersAndPosts {
	
		private List<Users> userList = new ArrayList<Users>();
		
		public FetchUsersAndPosts() {
			
			String createTable = DB_Helper.createUsersTable();
			String dataQuery;
			
			try {
				Class.forName(DB_Helper.JDBC_DRIVER);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Connection conn = null;
			Statement statement= null;
			try {
				System.out.println("Gonna try to connect now.");
				
				conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_Helper.DB_NAME, DB_UserData.USER, DB_UserData.PASS);
				statement = conn.createStatement();
				String sql = createTable;
				System.out.println("SQL: "+sql);
				statement.execute(sql);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(statement != null)
						conn.close();
				}catch(SQLException se) {
				}
				try {
					if(conn != null)
						conn.close();
				}catch(SQLException se) {
				}
			}
				
				
		}
		
		public List<Users> getUsers(){
			return userList;
		}
		
}
