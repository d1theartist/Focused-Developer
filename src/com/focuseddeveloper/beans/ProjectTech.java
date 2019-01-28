package com.focuseddeveloper.beans;

import java.util.ArrayList;

public class ProjectTech {
	
	private String title;
	private String subtitle;
	private String summary;
	
	private String webTitle;
	
	private String imagePath;

	private ArrayList<Project> projectsList = new ArrayList<Project>();

	public ProjectTech() {
		title = "C++ Projects";
		subtitle = "Testing... The Foundation";
		summary = "C++ is the first object oriented programming languaged that I studied.  Luckily for me, many of the technologies in use today are based on the C languages.  My first few projects were small"
				+ "blah blah blah....";
		
		setWebTitle();
		
	}
	
	public ProjectTech(String title, String subtitle, String summary, String imagePath) {
		this.title = title;
		this.subtitle = subtitle;
		this.summary = summary;
		this.imagePath = imagePath;
		setWebTitle();
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	
	public String getWebTitle() {
		return webTitle;
	}

	public void setWebTitle(String webTitle) {
		this.webTitle = title.replaceAll(" ","-");
	}
	
	public void setWebTitle() {
		this.webTitle = title.toLowerCase().replaceAll(" ","-").replaceAll("[^a-zA-Z0-9_-]", "");
	}
	

}
