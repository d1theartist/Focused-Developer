package com.focuseddeveloper.servedata;

import java.util.ArrayList;

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.ProjectTech;

public class ProjectData {

	private ArrayList<ProjectTech> techList = new ArrayList<ProjectTech>();
	private ArrayList<String> techs = new ArrayList<String>();
	
	public ArrayList<ProjectTech> getTechList() {
		
		return techList;
	}
	
	ProjectData(){
		techs.add("C++");
		techs.add("Java");
		techs.add("Android");
		
		for (String tech : techs) {
			//System.out.println(tech);
			techList.add( getProjectTech(tech) );
		}
		
	}
	
	private ProjectTech getProjectTech(String techName) {
		ProjectTech temp = new ProjectTech();
		temp.setTitle(techName);
		switch (techName){
			case "C++": 
				temp.setSubtitle("Testing... The Foundation");
				temp.setSummary("C++ is the first object oriented programming languaged that I studied.  Luckily for me, many of the technologies in use today are based on the C languages.  My first few projects were small"
						+ "blah blah blah....");
				// TBD: add features... will now change features to be dynamic
				// 		features will be an arrayList of String arrays
				
		}
		
		return temp;
	}
	

}
