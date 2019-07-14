package com.focuseddeveloper.servedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.focuseddeveloper.beans.Post;
import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.database.DB_Helper;
import com.focuseddeveloper.database.DB_UserData;

public class FetchPost {

	private List<Post> postList = new ArrayList<Post>();
	
	public FetchPost() {
		
		String createTable = DB_Helper.createPostTable();
		String dataQuery = DB_Helper.queryPostTable();
		
		System.out.println("Create Table: " + createTable);
		System.out.println("Query Table: " + dataQuery);
		
		
		try {
			Class.forName(DB_Helper.JDBC_DRIVER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection conn = null;
		Statement statement= null;
		
		try {
			
			
			System.out.println("FetchPost, constructor.  Pre initial connection.");
		
			conn = DriverManager.getConnection(DB_Helper.DB_URL+DB_Helper.DB_NAME, DB_UserData.USER, DB_UserData.PASS);
			statement = conn.createStatement();
			String sql = createTable;
			System.out.println("SQL: "+sql);
			
			statement.execute(sql);
			
			sql = dataQuery;
			System.out.println("SQL: "+sql);
			ResultSet results = statement.executeQuery(sql);

			System.out.println("SQL: Executed");
			
			int currentPostID = 0;
			int previousPostID = 0;
			int currentChildID = 0;
			int previousChildID = 0;
			int currentGrandChildID = 0;
			int previousGrandChildID = 0;
			
			Post newPost = null;
			Post newChild = null;
			Post newGrandChild = null;
			
			while(results.next() ) {
				System.out.println("We have results!");					
				
				currentPostID = results.getInt(DB_Helper.POST_ID);
			//	public Post(int ID, int userID, int parentID, String userName,
			//			String topic, String message, LocalDateTime date) {
				
				if(currentPostID !=  previousPostID) {
					newPost = new Post(results.getInt(DB_Helper.POST_ID),results.getInt(DB_Helper.POSTERS_ID),
							results.getInt(DB_Helper.POST_PARENT_ID), results.getString(DB_Helper.POSTERS_NAME),
							results.getString(DB_Helper.POST_TOPIC), results.getString(DB_Helper.POST_MESSAGE),
							results.getDate(DB_Helper.POST_DATE).toLocalDate());
					postList.add(newPost);
					
				}
				
				currentChildID = results.getInt(DB_Helper.CHILDID);
				
				if((currentChildID != 0 ) && (currentChildID !=  previousChildID) ) {
					newChild = new Post(results.getInt(DB_Helper.CHILDID),results.getInt(DB_Helper.CHILD_POSTERS_ID),
							results.getInt(DB_Helper.CHILD_PARENT_ID), results.getString(DB_Helper.CHILD_POSTERS_NAME),
							results.getString(DB_Helper.CHILD_TOPIC), results.getString(DB_Helper.CHILD_MESSAGE),
							results.getDate(DB_Helper.CHILD_DATE).toLocalDate());
					newPost.addReply(newChild);
					
				}
			
			currentGrandChildID = results.getInt(DB_Helper.G_CHILDID);
			
			if((currentGrandChildID != 0 ) && (currentGrandChildID !=  previousGrandChildID) ){
				newGrandChild = new Post(results.getInt(DB_Helper.G_CHILDID),results.getInt(DB_Helper.G_CHILD_POSTERS_ID),
						results.getInt(DB_Helper.G_CHILD_PARENT_ID), results.getString(DB_Helper.G_CHILD_POSTERS_NAME),
						results.getString(DB_Helper.G_CHILD_TOPIC), results.getString(DB_Helper.G_CHILD_MESSAGE),
						results.getDate(DB_Helper.G_CHILD_DATE).toLocalDate());
				newGrandChild.addReply(newChild);
				
			}
			
			previousPostID = currentPostID;
			previousChildID = currentChildID;
			previousGrandChildID = currentGrandChildID;
			
				
			} 
		}catch (SQLException e) {
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
		
	}
	
	public List<Post> getPosts(){
		return postList;
	}
	
}
