package com.focuseddeveloper.servedata;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;
import com.focuseddeveloper.service.Email_UserData;

public class FetchUsersAndPosts {
	
		private List<Users> userList = new ArrayList<Users>();
		
		public FetchUsersAndPosts() {
			
			String createTable = DB_Helper.createUsersTable();
			String dataQuery = DB_Helper.queryUserTable();
			
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
				
				sql = dataQuery;
				ResultSet results = statement.executeQuery(sql);
				int currentUser = 0;
				int previousUser = 0;
				
				Users newUser;
				
				while(results.next() ) {
					System.out.println("We have results!");					
					
					currentUser = results.getInt(DB_Helper.USER_ID);
					
					if(currentUser !=  previousUser) {
						newUser = new Users(results.getInt(DB_Helper.USER_ID), results.getString(DB_Helper.USER_ACCESS), results.getString(DB_Helper.USER_EMAIL), results.getString(DB_Helper.USER_PASSWORD), results.getString(DB_Helper.USER_NAME) );
						userList.add(newUser);
					}
				}
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
			
			if(!adminAdded() ) {
				addAdmin();
			}
		}
		
		public List<Users> getUsers(){
			return userList;
		}
		
		public boolean addUser(Users newUser) {
			
			String addUser;
			
			try {
				Class.forName(DB_Helper.JDBC_DRIVER);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Connection conn = null;
			Statement statement= null;
			addUser = DB_Helper.addUser(newUser );
			
			int resultInt = 0;
			
			try {
				System.out.println("Gonna try to connect now.");
				
				conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_Helper.DB_NAME, DB_UserData.USER, DB_UserData.PASS);
				statement = conn.createStatement();
				String sql = addUser;
				//System.out.println("SQL: "+sql);
				resultInt = statement.executeUpdate(sql);
				
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
			
			if(resultInt< 0)
				return true;
			return false;
		}
		
		private void addAdmin()  {
	        Users admin = new Users(0, Users.ACCESS_ADMIN, Email_UserData.ADMIN_USER, Email_UserData.ADMIN_PASS, "Charles");
	        addUser( admin);
		}
		
		public boolean adminAdded() {
			for(Users user: userList) {
				if( user.getEmail().equals(Email_UserData.ADMIN_USER) )
					return true;
			}
			return false;
		}

}
