package com.focuseddeveloper.servedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.ProjectTech;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;

public class CreateProjects {
	
	private ArrayList<String[]> keyFeaturesExample = new ArrayList<String[]>(); 
	
	static private String C = CreateProjectTechs.C;
	static private String JAVA = CreateProjectTechs.JAVA;
	static private String ANDROID =CreateProjectTechs.ANDROID;
	
	static private String subTitleExample = "subtitles are cool!";
	
	static private String summaryExample = "This is a summary of what the project.  I include the basic functionality of the project, and discuss what"
			+ " I found interesting and challenging about the project.";

	private ArrayList<Project> projectList = new ArrayList<Project>();
	
	private ArrayList<String> tagsExample = new ArrayList<String>();
	
	private String urlStringExample = "https://github.com/"; 
		
	public CreateProjects()
	 {
		try {
			Class.forName(DB_Helper.JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection conn;
		try {
			System.out.println("Gonna try to connect now.");
			
			conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_Helper.DB_NAME, DB_UserData.USER, DB_UserData.PASS);
			Statement statement = conn.createStatement();
			String sql = "SELECT * from "+DB_Helper.PROJECT_TABLE;
			System.out.println("SQL: "+sql);
			statement.execute(sql);
		
			ResultSet results = statement.getResultSet();
			
			while(results.next() ) {
				System.out.println("We have results!");
				String primaryTech = results.getString(DB_Helper.PRIMARY_TECH);
				String title = results.getString(DB_Helper.TITLE);
				String subtitle = results.getString(DB_Helper.SUBTITLE);
				String summary = results.getString(DB_Helper.SUMMARY);
				String urlString = results.getString(DB_Helper.URL_STRING);
		
				System.out.println("Title: "+title);
				
				//String title = rs.getString(DB_Helper.TITLE);
				
				Project project = new Project(primaryTech,title,subtitle,summary,urlString,"image");
				
				// public Project(String primaryProjectTech, String title, String subtitle, String summary, String urlString, String imagePath)
				
				//Get Key Features
				sql = "SELECT feature_name, featue_desc FROM "
						+ "key_features "
						+ "WHERE project_title "+title;
				System.out.println("SQL: "+sql);
				ResultSet kf_results = statement.getResultSet();
				ResultSetMetaData rsmd = kf_results.getMetaData();
				String c1 = rsmd.getColumnName(1);
				String c2 = rsmd.getColumnName(2);
				System.out.println("C1: "+c1 +", C2: "+c2);
				while(kf_results.next() ) {
					System.out.println("We have results from get Key Features!");
					String featName = kf_results.getString(DB_Helper.FEATURE_NAME);
					String featDesc = kf_results.getString(DB_Helper.FEATURE_DESC);
					project.addKeyFeature(new String[] {featName,featDesc} );
				}
				projectList.add(project);
			};
			conn.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		/*
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
		*/
	}
	
	public ArrayList<Project> getProjects() {
		return projectList;
	}
	

}
