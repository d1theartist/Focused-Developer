package com.focuseddeveloper.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServeHome
 */
@WebServlet("/home")
public class ServeHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();

		switch (page) {
		
		case "home":
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		case "about":
			request.getRequestDispatcher("about.jsp").forward(request, response);
			break;
		case "contact":
			request.getRequestDispatcher("contact.jsp").forward(request, response);
			break;
		default: 
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/projects/c.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
