package com.focuseddeveloper.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.servedata.CreateProjectTechs;
import com.focuseddeveloper.servedata.CreateProjects;

/**
 * Servlet implementation class ServeProjects
 */
@WebServlet("/project")
public class ServeProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TBD: get techList from ProjectData class
				// in prep for flexibility and incoming database
				
				ArrayList<Project> projects = new ArrayList<Project>();
				//CreateProjects pj = new CreateProjects();
				CreateProjectTechs pj;
				try {
					pj = new CreateProjectTechs();
					
					projects = pj.getProjList();
					
					Project selectedProject = new Project();
					
					String page = request.getParameter("page");
					page = page.toLowerCase();
					
					selectedProject = retrieveProject(projects,page);			
					
					if(selectedProject != null) {
						request.setAttribute("project", selectedProject);
						request.setAttribute("title",selectedProject.getTitle() );
						request.getRequestDispatcher("project.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				

	}
	
	private Project retrieveProject(ArrayList<Project> projects, String webname) {
		Project myProject = null;
		for(Project proj: projects) {
			if(proj.getWebTitle().equals(webname)) {
				myProject = new Project();
				myProject = proj;
			}
		}
		return myProject;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
