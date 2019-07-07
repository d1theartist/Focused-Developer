package com.focuseddeveloper.login;

import java.io.IOException;
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
		
		
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		Users currentUser = validateUserLogin(userEmail, userPassword);
		
		if( currentUser != null) {
			System.out.println("login success!");
			session.setAttribute("userEmail", currentUser.getEmail());
			session.setAttribute("userName", currentUser.getName());
			response.sendRedirect("index.jsp");
		}else {
			System.out.println("Login Failed");
			session.setAttribute("attempted","failed");
			response.sendRedirect("login.jsp");
		}
	}

	private Users validateUserLogin(String currentEmail, String currentPassword) {
		FetchUsersAndPosts fetchUsers;
		List<Users> userList = new ArrayList<Users>();
		
		fetchUsers = new FetchUsersAndPosts();
		userList = fetchUsers.getUsers();
		
		Users currentUser = null;
		for(Users user: userList) {
			if( user.getEmail().equals(currentEmail ) ) {
				if( user.getPassword().equals(currentPassword ) ) {
					currentUser = new Users(user);
					return currentUser;
				}else {
					return null;
				}
			}
		}
		return null;
	}
		

}
