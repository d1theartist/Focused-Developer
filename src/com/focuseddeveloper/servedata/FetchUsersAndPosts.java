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
import com.focuseddeveloper.login.HashPasswords;
import com.focuseddeveloper.login.LoginServlet;
import com.focuseddeveloper.service.Email_UserData;

public class FetchUsersAndPosts {
	
		private List<Users> userList = new ArrayList<Users>();
		private static final String PW_HOLDER = "dummy_password";
		
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
				System.out.println("FetchUsers, constructor.  Pre initial connection.");
				
				conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_Helper.DB_NAME, DB_UserData.USER, DB_UserData.PASS);
				statement = conn.createStatement();
				String sql = createTable;
				System.out.println("SQL: "+sql);
				statement.execute(sql);
				
				sql = dataQuery;
				ResultSet results = statement.executeQuery(sql);
				int currentUserID = 0;
				int previousUserID = 0;
				
				Users newUser;
				
				while(results.next() ) {
					System.out.println("We have results!");					
					
					currentUserID = results.getInt(DB_Helper.USER_ID);
					
					if(currentUserID !=  previousUserID) {
						newUser = new Users(results.getInt(DB_Helper.USER_ID), results.getString(DB_Helper.USER_ACCESS), results.getString(DB_Helper.USER_EMAIL), PW_HOLDER, results.getString(DB_Helper.USER_NAME) );
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
			
			
			addAdmin();
			
		}
		
		public List<Users> getUsers(){
			return userList;
		}
		
		public boolean addUser(Users newUser) {
			
			if(emailInUse(newUser)) {
				System.out.println("Will not add new user.  Email is in use.");
				return false;
			}
			
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
				System.out.println("SQL: "+sql);
				resultInt = statement.executeUpdate(sql);
				
				if(resultInt > 0) {
					userList.add(newUser);
					System.out.println("New User added: " + newUser.getName());
				}else {
					System.out.println("Failed to add: " + newUser.getName() + " to the user table.");
				}
				
			}catch (SQLException e) {
				System.out.println("SQLException: " + e.getLocalizedMessage() );
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
			
			if(resultInt > 0)
				return true;
			return false;
		}
		
		private void addAdmin()  {
			System.out.println("Adding admin.");
			String salt = HashPasswords.generateSalt(512).get();
			System.out.println("Salt Length: "+ salt.length());
			String password = HashPasswords.hashPassword(Email_UserData.ADMIN_PASS, salt).get();
			System.gc();				
	        Users admin = new Users(0, Users.ACCESS_ADMIN, Email_UserData.ADMIN_USER, password, "Charles");
	        admin.setSalt(salt);
	        addUser( admin);
		}
		
		public boolean emailInUse(Users newUser) {
			if( LoginServlet.validateEmail(newUser.getEmail()) != null ) {
				System.out.println("Email address is already in use: " + newUser.getEmail());
				return true;
			}
			return false;
		}
		

}
