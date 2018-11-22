package com.focuseddeveloper.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.ProjectTech;

/**
 * Servlet implementation class ServeProjectTechs
 */
@WebServlet(description = "Serves the projects to the appropriate jsp pages", urlPatterns = { "/projects" })
public class ServeProjectTechs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeProjectTechs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProjectTech projTech = new ProjectTech();
		Project project1 = new Project();
		
		projTech.addProject(project1);
		
		request.setAttribute("projectTech", projTech);
		
		String page = request.getParameter("page");
		page = page.toLowerCase();

		switch (page) {
		
		case "c":
			request.getRequestDispatcher("c.jsp").forward(request, response);
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
