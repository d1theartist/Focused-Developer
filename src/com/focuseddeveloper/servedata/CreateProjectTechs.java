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

import com.focuseddeveloper.beans.Project;
import com.focuseddeveloper.beans.ProjectTech;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;

public class CreateProjectTechs {
	
	private ArrayList<ProjectTech> techList = new ArrayList<ProjectTech>();
	private ArrayList<Project> projList = new ArrayList<Project>();
	
	static public final String C = "C++";
	static public final String JAVA = "Java";
	static public final String ANDROID = "Android";
	

	public static final String DB_NAME = "FOCUSEDDEV_DB";
	
	
	//@Resource(name="jdbc/focuseddeveloper")
	//private DataSource dataSource;
	
	static private String subTitleExample = "I like this Language! From CreateProjectTask";
	
	static private String summaryExample = "Brief description of the language.  I'll give my general thoughts on what I like to use it for.  Perhaps even "
			+ "discuss how and why I decided to get started with this particular tech. Testing!";
	
	static private String dataQuery = "SELECT technologies.tech_id, technologies.title as tech_title, technologies.subtitle as tech_subtitle, technologies.summary as tech_summary, technologies.tech_image as tech_image,\r\n" + 
			"projects.project_id, projects.title as proj_title, projects.subtitle as proj_subtitle, projects.summary as proj_summary, projects.project_image as proj_image,\r\n" + 
			"feature_name, key_features.feature_desc\r\n" + 
			"from technologies left outer join projects on technologies.tech_id = projects.tech_id \r\n" + 
			"left outer join key_features on projects.project_id = key_features.project_id";
	
	public CreateProjectTechs() throws NamingException
	{
		//System.out.println("I'm in createProjectTech.");		
		try {
			Class.forName(DB_Helper.JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection conn = null;
		Statement statement = null;
		try {
			System.out.println("Gonna try to connect now.");
			
			conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_Helper.DB_NAME, DB_UserData.USER, DB_UserData.PASS);
			statement = conn.createStatement();
			//String sql = "SELECT * from "+DB_Helper.PROJECT_TECH_TABLE;
			String sql = dataQuery;
			System.out.println("SQL: "+sql);
			statement.execute(sql);
			
			int currentTech = 0;
			int currentProj = 0;
			int prevTech = 0;
			int prevProj = 0;
			
			String featureName = null;
			
			ProjectTech newProjectTech = null;
			Project newProject = null;
			
			ResultSet results = statement.getResultSet();
			
			while(results.next() ) {
				System.out.println("We have results!");
				
				
				
				currentTech = results.getInt("tech_id");
				
				if(currentTech !=  prevTech) {
					newProjectTech = new ProjectTech(results.getString("tech_title"), results.getString("tech_subtitle"), results.getString("tech_summary"), results.getString("tech_image"));
					techList.add(newProjectTech);
				}
				
				
				currentProj = results.getInt("project_id");
				if(currentProj != prevProj) {
					newProject = new Project(results.getString("tech_title"), results.getString("proj_title"), results.getString("proj_subtitle"), results.getString("proj_summary"), "githut.com", results.getString("proj_image"));
					projList.add(newProject);
					newProjectTech.addProject(newProject);
				}
				
				
				featureName = results.getString("feature_name");
				
				if(featureName != null) {
					newProject.addKeyFeature(new String[]  {results.getString("feature_name"),results.getString("feature_desc")} );
				}
				
				prevTech = currentTech;
				prevProj = currentProj;
				featureName = null;
				
				
				/*
				//String title = results.getString(DB_Helper.TITLE);
				//String subtitle = results.getString(DB_Helper.SUBTITLE);
				//System.out.print("Title: "+title);
				
				//String title = rs.getString(DB_Helper.TITLE);
				//ProjectTech projectTech = new ProjectTech(title,subtitle,summaryExample);
				 * 
				 
				techList.add(projectTech);
				*/
			};
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			try {
				if(statement != null)
					conn.close();
			}catch(SQLException se) {
			}
			try {
				if(conn != null)
					conn.close();
			}catch(SQLException se) {
			}
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
	
	public ArrayList<Project> getProjList(){
		return projList;
	}

}
