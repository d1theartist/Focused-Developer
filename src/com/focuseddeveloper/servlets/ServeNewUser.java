package com.focuseddeveloper.servlets;

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

import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;
import com.focuseddeveloper.login.HashPasswords;
import com.focuseddeveloper.servedata.FetchUsersAndPosts;
import com.focuseddeveloper.service.Email_UserData;

/**
 * Servlet implementation class ServeNewUser
 */
@WebServlet("/ServeNewUser")
public class ServeNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Users> userList = new ArrayList<Users>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeNewUser() {
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
		FetchUsersAndPosts fetchUsers;
		
		fetchUsers = new FetchUsersAndPosts();
		userList =  (ArrayList<Users>) fetchUsers.getUsers();
		
		// String access, String email, String password, String name) 
		String access = request.getParameter("access");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		System.out.println("password: " + password);
				
		if(isEmailInUse(email)) {
			response.sendRedirect("login.jsp");
			password = null;
			System.gc();
			// TODO: Create a welcome page
		}else {
			String salt = HashPasswords.generateSalt(512).get();
			password = HashPasswords.hashPassword(password, salt).get();
			System.gc();
			
			System.out.println("hashed password: " + password);
			System.out.println("hashed salt: " + salt);
			
			Users newUser = new Users(0, access, email, password, name);
			newUser.setSalt(salt);
			
			boolean userAdded = fetchUsers.addUser(newUser);

			if(userAdded) {
				//goToLogin(request,response);
				response.sendRedirect("login.jsp");
			}else {
				response.sendRedirect("signup.jsp");
			}
		}
		
	}
	
	public boolean isEmailInUse(String newEmail) {
		//if user's email is already in use, reject the new account
		for(Users user: userList) {
			if( user.getEmail().equals(newEmail) ) {
				return true;
			}
		}
		return false;
	}
	
	
	

}
