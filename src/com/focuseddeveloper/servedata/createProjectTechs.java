package com.focuseddeveloper.servedata;

import java.util.ArrayList;

import com.focuseddeveloper.beans.ProjectTech;

public class createProjectTechs {
	
	private ArrayList<ProjectTech> techList = new ArrayList<ProjectTech>();
	
	static public final String C = "C++";
	static public final String JAVA = "Java";
	static public final String ANDROID = "Android";
	
	static private String subTitleExample = "I like this Language!";
	
	static private String summaryExample = "Brief description of the language.  I'll give my general thoughts on what I like to use it for.  Perhaps even "
			+ "discuss how and why I decided to get started with this particular tech.";
	
	public createProjectTechs()
	{
		techList.add(new ProjectTech(C,subTitleExample,summaryExample) );
		techList.add(new ProjectTech(JAVA,subTitleExample,summaryExample) );
		techList.add(new ProjectTech(ANDROID,subTitleExample,summaryExample) );
		
	}
	
	public ArrayList<ProjectTech> getTechList(){
		return techList;
	}

}
