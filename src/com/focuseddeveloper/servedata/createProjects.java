package com.focuseddeveloper.servedata;

import java.util.ArrayList;

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.ProjectTech;

public class createProjects {
	
	static private ArrayList<String[]> keyFeaturesExample = new ArrayList<String[]>(); 
	
	static private String c = "C=+";
	static private String java = "Java";
	static private String android = "Android";
	
	static private String subTitleExample = "subtitles are cool!";
	
	static private String summaryExample = "This is a summary of what the project.  I include the basic functionality of the project, and discuss what"
			+ " I found interesting and challenging about the project.";

	static private ArrayList<Project> projectList = new ArrayList<Project>();
	
	static private ArrayList<String> tagsExample = new ArrayList<String>();
	
	private String urlStringExample = "https://github.com/"; 
		
	
	{
		keyFeaturesExample.add(new String[] {"Feature 1","This is Awesome"});
		keyFeaturesExample.add(new String[] {"Feature 2","Amazing!!!"});
		keyFeaturesExample.add(new String[] {"Feature 3","Wait... there's even more!"});
		
		tagsExample.add("Language 1");
		tagsExample.add("tools 1");
		tagsExample.add("tools 2");
		
		projectList.add( new Project(c,"Battleship",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(c,"Video Store",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(java,"Focused Developer Site",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(java,"Business Transactions",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(android,"Abstract News",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(android,"Popular Movies",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		projectList.add( new Project(android,"Build it Bigger",subTitleExample,summaryExample, keyFeaturesExample, urlStringExample, tagsExample) );
		
	}
	
	public static ArrayList getProjects() {
		return projectList;
	}
	

}
