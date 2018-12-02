package com.focuseddeveloper.servedata;

import java.util.ArrayList;

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.ProjectTech;

public class createProjects {
	
	private ArrayList<String[]> keyFeaturesExample = new ArrayList<String[]>(); 
	
	static private String C = createProjectTechs.C;
	static private String JAVA = createProjectTechs.JAVA;
	static private String ANDROID =createProjectTechs.ANDROID;
	
	static private String subTitleExample = "subtitles are cool!";
	
	static private String summaryExample = "This is a summary of what the project.  I include the basic functionality of the project, and discuss what"
			+ " I found interesting and challenging about the project.";

	private ArrayList<Project> projectList = new ArrayList<Project>();
	
	private ArrayList<String> tagsExample = new ArrayList<String>();
	
	private String urlStringExample = "https://github.com/"; 
		
	public createProjects()
	 {
		keyFeaturesExample.add(new String[] {"Feature 1","This is Awesome"});
		keyFeaturesExample.add(new String[] {"Feature 2","Amazing!!!"});
		keyFeaturesExample.add(new String[] {"Feature 3","Wait... there's even more!"});
		
		tagsExample.add("Language 1");
		tagsExample.add("tools 1");
		tagsExample.add("tools 2");
		
		projectList.add( new Project(C,"Battleship",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(C,"Video Store",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(JAVA,"Focused Developer Site",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(JAVA,"Business Transactions",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(ANDROID,"Abstract News",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(ANDROID,"Popular Movies",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(ANDROID,"Build it Bigger",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		
	}
	
	public ArrayList getProjects() {
		return projectList;
	}
	

}
