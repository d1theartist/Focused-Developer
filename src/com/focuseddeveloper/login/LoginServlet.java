package com.focuseddeveloper.login;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;
import com.focuseddeveloper.servedata.FetchUsersAndPosts;
import com.focuseddeveloper.service.Email_UserData;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		
		try {
			Class.forName(DB_Helper.JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		Users userAccount = validateEmail(userEmail);
		
		
		if( userAccount != null) {
			System.out.println("password: " + userPassword);
			System.out.println("hashed password: " + userAccount.getPassword());
			System.out.println("hashed salt: " + userAccount.getSalt());
			
			
			if(HashPasswords.verifyPassword(userPassword, userAccount.getPassword(),  userAccount.getSalt())) {
				System.out.println("login success!");
				session.setAttribute("userEmail", userAccount.getEmail());
				session.setAttribute("userName", userAccount.getName());
				session.setAttribute("access", userAccount.getAccess());
				response.sendRedirect("index.jsp");
			}else {
				System.out.println("Login Failed: wrong password");
				session.setAttribute("attempted","failed");
				response.sendRedirect("login.jsp");
				// incorrect password
			}
		}else {
			System.out.println("Login Failed: incorrect email");
			session.setAttribute("attempted","failed");
			response.sendRedirect("login.jsp");
		}
	}

	/*
	private Users validateUserEmail(String currentEmail) {
		FetchUsersAndPosts fetchUsers;
		List<Users> userList = new ArrayList<Users>();
		
		fetchUsers = new FetchUsersAndPosts();
		userList = fetchUsers.getUsers();
		
		Users currentUser = null;
		for(Users user: userList) {
			if( user.getEmail().equals(currentEmail ) ) {
				currentUser = new Users(user);
				return currentUser;
			}
		}
		return null;
	}
	*/
		
	public static Users validateEmail(String email) {
		Connection conn = null;
		Statement statement = null;
		Users newUser = null;
		try {
			conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_Helper.DB_NAME, DB_UserData.USER, DB_UserData.PASS);
			statement = conn.createStatement();
			String sql = DB_Helper.queryUserTableForEmail(email);
			System.out.println("SQL: "+sql);
			statement.execute(sql);
			
			ResultSet results = statement.executeQuery(sql);
			System.out.println("Results: " + results.toString() );
			if(results.first()) {
				System.out.println("We have email results, userID: " + results.getInt(DB_Helper.USER_ID) );
				newUser = new Users(results.getInt(DB_Helper.USER_ID), results.getString(DB_Helper.USER_ACCESS),
						results.getString(DB_Helper.USER_EMAIL), results.getString(DB_Helper.USER_PASSWORD),
						results.getString(DB_Helper.USER_NAME));
				newUser.setSalt(results.getString(DB_Helper.USER_SALT));
				return newUser;
			}
		} catch (SQLException e) {
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
		return null;
		
		
	}
}
