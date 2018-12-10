package com.focuseddeveloper.servedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.focuseddeveloper.beans.ProjectTech;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;

public class CreateProjectTechs {
	
	private ArrayList<ProjectTech> techList = new ArrayList<ProjectTech>();
	
	static public final String C = "C++";
	static public final String JAVA = "Java";
	static public final String ANDROID = "Android";
	

	public static final String DB_NAME = "FOCUSEDDEV_DB";
	
	
	//@Resource(name="jdbc/focuseddeveloper")
	//private DataSource dataSource;
	
	static private String subTitleExample = "I like this Language! From CreateProjectTask";
	
	static private String summaryExample = "Brief description of the language.  I'll give my general thoughts on what I like to use it for.  Perhaps even "
			+ "discuss how and why I decided to get started with this particular tech. Testing!";
	
	public CreateProjectTechs() throws NamingException
	{
		//System.out.println("I'm in createProjectTech.");		
		try {
			Class.forName(DB_Helper.JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection conn;
		try {
			System.out.println("Gonna try to connect now.");
			
			conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_NAME, DB_UserData.USER, DB_UserData.PASS);
			Statement statement = conn.createStatement();
			String sql = "SELECT * from "+DB_Helper.PROJECT_TECH_TABLE;
			System.out.println("SQL: "+sql);
			statement.execute(sql);
		
			ResultSet results = statement.getResultSet();
			
			while(results.next() ) {
				System.out.println("We have results!");
				String title = results.getString(DB_Helper.TITLE);
				String subtitle = results.getString(DB_Helper.SUBTITLE);
				System.out.print("Title: "+title);
				
				//String title = rs.getString(DB_Helper.TITLE);
				ProjectTech projectTech = new ProjectTech(title,subtitle,summaryExample);
				techList.add(projectTech);
			};
			conn.close();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

		/*
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		connect  = dataSource.getConnection();
		
		String query = "Select * from "+ DB_Helper.PROJECT_TECH_TABLE;
		stmt = connect.createStatement();
		
		rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String title = rs.getString(DB_Helper.TITLE);
			String subtitle = rs.getString(DB_Helper.SUBTITLE);
			System.out.print("Title: "+title);
			
			//String title = rs.getString(DB_Helper.TITLE);
			ProjectTech projectTech = new ProjectTech(title,subtitle,summaryExample);
			techList.add(projectTech);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		techList.add(new ProjectTech(C,subTitleExample,summaryExample) );
		techList.add(new ProjectTech(JAVA,subTitleExample,summaryExample) );
		techList.add(new ProjectTech(ANDROID,subTitleExample,summaryExample) );
		*/
		
	}
	
	
	
	public ArrayList<ProjectTech> getTechList(){
		return techList;
	}

}
