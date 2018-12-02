package com.focuseddeveloper.servedata;

import java.util.ArrayList;

import com.focuseddeveloper.beans.ProjectTech;

public class createProjectTechs {
	
	private static ArrayList<ProjectTech> techList = new ArrayList<ProjectTech>();
	
	static private String c = "C=+";
	static private String java = "Java";
	static private String android = "Android";
	
	static private String subTitleExample = "I like this Language!";
	
	static private String summaryExample = "Brief description of the language.  I'll give my general thoughts on what I like to use it for.  Perhaps even "
			+ "discuss how and why I decided to get started with this particular tech.";
	
	{
		techList.add(new ProjectTech(c,subTitleExample,summaryExample) );
		techList.add(new ProjectTech(java,subTitleExample,summaryExample) );
		techList.add(new ProjectTech(android,subTitleExample,summaryExample) );
		
	}
	
	public ArrayList<ProjectTech> getTechList(){
		return techList;
	}

}
