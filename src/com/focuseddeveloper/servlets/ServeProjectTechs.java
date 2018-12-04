package com.focuseddeveloper.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.ProjectTech;
import com.focuseddeveloper.servedata.CreateProjectTechs;
import com.focuseddeveloper.servedata.CreateProjects;

/**
 * Servlet implementation class ServeProjectTechs
 */
@WebServlet(description = "Serves the projects to the appropriate jsp pages", urlPatterns = { "/techs" })
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
		
		// TBD: get techList from ProjectData class
		// in prep for flexibility and incoming database
		ArrayList<ProjectTech> techList = new ArrayList<ProjectTech>();
		CreateProjectTechs pt = new CreateProjectTechs();
		
		ArrayList<Project> projects = new ArrayList<Project>();
		CreateProjects pj = new CreateProjects();
		
		techList = pt.getTechList();
		projects = pj.getProjects();
			
		
		ProjectTech projTech = new ProjectTech();
		
		
		String page = request.getParameter("page");
		page = page.toLowerCase();
		
		projTech = retrieveTech(techList, page);
		
		retrieveProjects(projects,projTech);

		if(projTech != null) {
			request.setAttribute("projectTech", projTech);
			request.setAttribute("title",projTech.getTitle() );
			request.getRequestDispatcher("techs.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
	
	private ProjectTech retrieveTech(ArrayList<ProjectTech> techs, String techName) {
		ProjectTech myTech = null;
		for(ProjectTech projTech : techs) {
			if( projTech.getWebTitle().equals(techName) ) {
				myTech = new ProjectTech();
				myTech = projTech;
			}
		}
		return myTech;
	}
	
	private void retrieveProjects(ArrayList<Project> allProjects, ProjectTech projtech){
		for(Project proj: allProjects) {
			if( proj.getPrimaryProjectTech().equals(projtech.getTitle()) ) {
				projtech.addProject(proj);
			}
		}
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
