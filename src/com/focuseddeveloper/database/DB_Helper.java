package com.focuseddeveloper.database;

import java.time.LocalDateTime;

import com.focuseddeveloper.beans.Users;
import com.focuseddeveloper.beans.Post.PostType;

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
	
	public static final String POSTS_TABLE = "posts";
	public static final String POST_ID = "post_id";
	public static final String POSTERS_ID = "posters_id";
	public static final String POST_TYPE = "post_type";
	public static final String POST_TOPIC = "post_topic";
	public static final String POST_MESSAGE = "post_message";
	public static final String POST_DATE = "post_date";	
	
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
	
	
	public static String createPostTable() {
		String statement;
		
		statement = CREATE_TABLE + POSTS_TABLE
				+ "(" + POST_ID + " MEDIUMINT NOT NULL AUTO_INCREMENT, "
				+ POSTERS_ID + " MEDIUMINT NOT NULL, "
				+ POST_TYPE + " VARCHAR(20) NOT NULL, "
				+ POST_TOPIC + " VARCHAR(100) NOT NULL, "
				+ POST_MESSAGE + " VARCHAR(3500) NOT NULL, "
				+ POST_DATE + " DATE NOT NULL, "
				+ "PRIMARY KEY (" + POST_ID + "))";
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
		statement = "SELECT * from " + USERS_TABLE + " where " + USER_EMAIL + " = \"" + email + "\"";
		
		return statement;
	}
	
	public static String addUser(Users newUser) {
		String statement;
		statement = "INSERT INTO " + USERS_TABLE + "(" + USER_ACCESS + ", " + USER_EMAIL + ", " + USER_PASSWORD + ", "+ USER_SALT + ", " + USER_NAME + ") "
				+ "VALUES( '" + newUser.getAccess() + "', '" +newUser.getEmail() + "', '" + newUser.getPassword() + "', '"+ newUser.getSalt() + "', '" + newUser.getName() + "')";

		return statement;
	}
	
}
