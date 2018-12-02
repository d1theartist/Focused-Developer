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
import com.focuseddeveloper.servedata.createProjectTechs;
import com.focuseddeveloper.servedata.createProjects;

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
		
		// TBD: get techList from ProjectData class
		// in prep for flexibility and incoming database
		ArrayList<ProjectTech> techList = new ArrayList<ProjectTech>();
		createProjectTechs pt = new createProjectTechs();
		
		ArrayList<Project> projects = new ArrayList<Project>();
		createProjects pj = new createProjects();
		
		techList = pt.getTechList();
		projects = pj.getProjects();
		
		for(ProjectTech projTech : techList) {
			System.out.println("Project Title: "+projTech.getTitle());
		}
		
		
		ProjectTech projTech = new ProjectTech();
		/*
		Project project1 = new Project();
		Project project2 = new Project();
		
		project2.setTitle("Movie Store");
		project2.setSubtitle("movie subtitle");
		project2.setSummary("afkfaklmffoejowjgkdakldjsvlajfd adjo joajf aodjf jaojfa joajdfioajf osjfoajo jajfaos foiajfoajoifajsodf jaoisffjoaijfo jfiajfoa dfjaoijsfo ajsodf joifdjaosjfdaj");
		project2.setKeyFeatures(project1.getKeyFeatures() );
		
		projTech.addProject(project1);
		projTech.addProject(project2);
		request.setAttribute("projectTech", projTech);
		*/
		
		String page = request.getParameter("page");
		page = page.toLowerCase();

		switch (page) {
		
		case "c":
			projTech = retrieveTech(techList, createProjectTechs.C);
			retrieveProjects(projects,projTech);
			request.setAttribute("projectTech", projTech);
			request.setAttribute("title", "C++ Projects");
			request.getRequestDispatcher("techs.jsp").forward(request, response);
			break;
		case "java":
			projTech = retrieveTech(techList, createProjectTechs.JAVA);
			retrieveProjects(projects,projTech);
			request.setAttribute("projectTech", projTech);
			request.setAttribute("title", "Java Projects");
			request.getRequestDispatcher("techs.jsp").forward(request, response);
			break;
		case "android":
			projTech = retrieveTech(techList, createProjectTechs.ANDROID);
			retrieveProjects(projects,projTech);
			request.setAttribute("projectTech", projTech);
			request.setAttribute("title", "Android Projects");
			request.getRequestDispatcher("techs.jsp").forward(request, response);
			break;
		default: 
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}		
		
	}
	
	private ProjectTech retrieveTech(ArrayList<ProjectTech> techs, String techName) {
		for(ProjectTech projTech : techs) {
			if( projTech.getTitle().equals(techName) ) {
				return projTech;
			}
		}
		return null;
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
