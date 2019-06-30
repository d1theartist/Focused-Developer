package com.focuseddeveloper.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.focuseddeveloper.database.DB_UserData;

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
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		
		if(validateUser(userEmail, userPassword)) {
			System.out.println("login success!");
			HttpSession session = request.getSession();
			session.setAttribute("userEmail", userEmail);
			session.setAttribute("userName", "Charles");
			response.sendRedirect("welcome.jsp");
		}else {
			//todo login failed
			response.sendRedirect("login.jsp");
		}
	}

	private boolean validateUser(String userEmail, String userPassword) {
		// TODO Auto-generated method stub
		String email = DB_UserData.USER;
		String pass = DB_UserData.PASS;
		
		if(userEmail.equals(email)&&userPassword.equals(pass)) {
			
			return true;
		}
			
		return false;
	}

}
