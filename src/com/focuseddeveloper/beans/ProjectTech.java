package com.focuseddeveloper.beans;

import java.util.ArrayList;

public class ProjectTech {
	
	private String title;
	private String subtitle;
	private String summary;
	

	private ArrayList<Project> projectsList = new ArrayList<Project>();

	public ProjectTech() {
		title = "C++ Projects";
		subtitle = "Testing... The Foundation";
		summary = "C++ is the first object oriented programming languaged that I studied.  Luckily for me, many of the technologies in use today are based on the C languages.  My first few projects were small"
				+ "blah blah blah....";
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ArrayList<Project> getProjectsList() {
		return projectsList;
	}

	public void setProjectsList(ArrayList<Project> projectsList) {
		this.projectsList = projectsList;
	}

	public void addProject(Project newProject)
	{
	       this.projectsList.add(newProject);
	}
	

}
