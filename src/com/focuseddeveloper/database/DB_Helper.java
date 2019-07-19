package com.focuseddeveloper.database;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.beans.Post;

public class DB_Helper {

	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://localhost:3306/";

	
	   
	public static final String DB_NAME = "FOCUSEDDEV_DB";
	
	public static final String PROJECT_TECH_TABLE = "technologies";
	public static final String TITLE = "title";
	public static final String SUBTITLE = "subtitle";
	public static final String SUMMARY = "summary";
	public static final String WEB_TITLE = "web_title";
	public static final String PROJECT_TECH_ID = "project_tech_id";
	
	public static final String PROJECT_TABLE = "projects";
	public static final String URL_STRING = "url_string";
	public static final String PROJECT_ID = "project_id";
	public static final String PRIMARY_TECH = "projecttech_title";
	
	public static final String KEY_FEATURES_TABLE = "key_features";
	public static final String FEATURE_NAME = "feature_name";
	public static final String FEATURE_DESC = "feature_desc";
	public static final String FEATURE_ID = "feature_id";
	
	public static final String USERS_TABLE = "users";
	public static final String USER_ID = "user_id";
	public static final String USER_ACCESS = "access_level";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_SALT = "user_salt";
	public static final String USER_NAME = "user_name";
	
	public static final String POSTS_TABLE_TEST = "posts_test";
	public static final String POSTS_TABLE = "posts";
	public static final String POST_ID = "post_id";
	public static final String POSTERS_ID = "posters_id";
	public static final String POSTERS_NAME = "posters_name";
	public static final String POST_PARENT_ID = "post_parent_id";
	public static final String POST_TOPIC = "post_topic";
	public static final String POST_MESSAGE = "post_message";
	public static final String POST_DATE = "post_date";	
	
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
	
	
	public static String createPostTable() {
		String statement;
		
		statement = CREATE_TABLE + POSTS_TABLE_TEST
				+ "(" + POST_ID + " MEDIUMINT NOT NULL AUTO_INCREMENT, "
				+ POSTERS_ID + " MEDIUMINT NOT NULL, "
				+ POSTERS_NAME + " VARCHAR(20) NOT NULL, "
				+ POST_PARENT_ID + " MEDIUMINT NOT NULL, "
				+ POST_TOPIC + " VARCHAR(100) NOT NULL, "
				+ POST_MESSAGE + " VARCHAR(3500) NOT NULL, "
				+ POST_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
				+ "PRIMARY KEY (" + POST_ID + "))";
		return statement;
	}
	
	
	public static String queryPostTable() { 
		String statement = "SELECT main.post_id, main.posters_id, main.post_parent_id, main.posters_name, main.post_topic, main.post_message, main.post_date, \r\n" + 
				"r1.post_id as child_id, r1.post_parent_id as child_parent,r2.posters_id as child_posters_id, r1.posters_name as child_posters_name, r1.post_topic as child_topic, r1.post_message as child_message, r1.post_date as child_date,\r\n" + 
				"r2.post_id as g_child_id, r2.post_parent_id as g_child_parent, r2.posters_id as g_child_posters_id,r2.posters_name as g_child_posters_name, r2.post_topic as g_child_topic, r2.post_message as g_child_message, r2.post_date as g_child_date\r\n" + 
				"FROM posts_test as main \r\n" + 
				"left outer join posts_test as r1 on main.post_id = r1.post_parent_id\r\n" + 
				"left outer join posts_test as r2 on r1.post_id = r2.post_parent_id  where main.post_parent_id = 0\r\n" + 
				"Order By post_id desc, child_id asc, g_child_id asc";
		return statement;
	}
	
	public static String CHILDID = "child_id";
	public static String CHILD_PARENT_ID = "child_parent";
	public static String CHILD_POSTERS_ID = "child_posters_id";
	public static String CHILD_POSTERS_NAME = "child_posters_name";
	public static String CHILD_TOPIC = "child_topic";
	public static String CHILD_MESSAGE = "child_message";
	public static String CHILD_DATE = "child_date";
	
	public static String G_CHILDID = "g_child_id";
	public static String G_CHILD_PARENT_ID = "g_child_parent";
	public static String G_CHILD_POSTERS_ID = "g_child_posters_id";
	public static String G_CHILD_POSTERS_NAME = "g_child_posters_name";
	public static String G_CHILD_TOPIC = "g_child_topic";
	public static String G_CHILD_MESSAGE = "g_child_message";
	public static String G_CHILD_DATE = "g_child_date";
	
	/*
	public static String queryPostTable() { 
		String statement;
		
		statement = "SELECT topics." + POST_ID + ", topics."  + POSTERS_ID + ", topics." + POST_TOPIC + ", topics." + POST_MESSAGE + ", topics." + POST_DATE + ",\r\n"
		+ " replies." + POST_ID + ", replies. +"  + POSTERS_ID + ", replies." + POST_TOPIC + ", replies." + POST_MESSAGE + ", replies." + POST_DATE + ",\r\n"
		+ "FROM "+ POSTS_TABLE + " as topics LEFT OUTER JOIN " + POSTS_TABLE + " as replies ON topics." + POST_ID + " = replies." + POST_PARENT_ID;
						
		return statement;
	}*/
	
	public static String addPost(Post newPost) {
		String statement;
		statement = "INSERT INTO " + POSTS_TABLE_TEST + "( " + POSTERS_ID + ", " + POST_PARENT_ID +", "+ POSTERS_NAME +", " + POST_TOPIC + ", "+ POST_MESSAGE + ") "
				+ "VALUES( '" + newPost.getUserID() + "', '" +newPost.getParentID() + "', '"+newPost.getUserName() + "', '" + newPost.getTopic() + "', '" + newPost.getMessage() + "')";

		return statement;
	}
	
	public static String createUsersTable() {
		String statement;
		
		statement = CREATE_TABLE + USERS_TABLE
				+ "(" + USER_ID + " MEDIUMINT NOT NULL AUTO_INCREMENT, "
				+ USER_ACCESS + " VARCHAR(20) NOT NULL, "
				+ USER_EMAIL + " VARCHAR(50) NOT NULL, "
				+ USER_PASSWORD + " VARCHAR(1512) NOT NULL, "
				+ USER_SALT + " VARCHAR(1512) NOT NULL, "
				+ USER_NAME + " VARCHAR(20) NOT NULL, "
				+ "PRIMARY KEY (" + USER_ID + "))";
		return statement;
	}
	
	
	
	public static String queryUserTable() {
		String statement;
		statement = "SELECT "+ USER_ACCESS + ", " + USER_EMAIL + ", "+ USER_NAME + " from " + USERS_TABLE;
		
		return statement;
	}
	
	public static String queryUserTablePassword(int userID) {
		String statement;
		statement = "SELECT "+ USER_PASSWORD + ", " + USER_SALT + " from " + USERS_TABLE + " where " + USER_ID + " = " + userID;
		
		return statement;
	}
	
	public static String queryUserTableForEmail(String email) {
		String statement;
		statement = "SELECT * from " + USERS_TABLE + " where " +  USER_EMAIL + " = \"" + email + "\"";
		
		return statement;
	}
	
	public static String addUser(Users newUser) {
		String statement;
		statement = "INSERT INTO " + USERS_TABLE + "(" + USER_ACCESS + ", " + USER_EMAIL + ", " + USER_PASSWORD + ", "+ USER_SALT + ", " + USER_NAME + ") "
				+ "VALUES( '" + newUser.getAccess() + "', '" +newUser.getEmail() + "', '" + newUser.getPassword() + "', '"+ newUser.getSalt() + "', '" + newUser.getName() + "')";

		return statement;
	}
	
}
