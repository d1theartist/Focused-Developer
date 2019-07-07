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
        FetchUsersAndPosts fetchUsers;
		
		fetchUsers = new FetchUsersAndPosts();
		userList =  (ArrayList<Users>) fetchUsers.getUsers();
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
		
		// String access, String email, String password, String name) 
		String access = request.getParameter("access");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		Users newUser = new Users(0, access, email, password, name);
		
		if(doesUserExist(newUser)) {
			response.sendRedirect("login.jsp");
			// TODO: Create a welcome page
		}else {
		
			boolean userAdded = new FetchUsersAndPosts().addUser(newUser);

			if(userAdded) {
				//goToLogin(request,response);
				response.sendRedirect("login.jsp");
			}else {
				response.sendRedirect("signup.jsp");
			}
		}
		
	}
	
	public boolean doesUserExist(Users newUser) {
		//if user's email is already in use, reject the new account
		for(Users user: userList) {
			if( user.getEmail().equals(newUser.getEmail() ) ) {
				return true;
			}
		}
		return false;
	}
	

}
