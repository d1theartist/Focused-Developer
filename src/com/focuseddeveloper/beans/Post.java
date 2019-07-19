package com.focuseddeveloper.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
	

	
	private int ID;
	private int userID;
	private int parentID;
	private String userName;
	private String topic;
	private String message;
	private LocalDate date;
	ArrayList<Post> replies;
	private boolean replyEligible;
	

	public Post() {
		this.ID = 0;
		this.userID = 0;
		this.parentID = 0;
		this.userName = "Name";
		this.topic = "Topic";
		this.message = "Post message";
		this.date = LocalDate.now();
		this.replies = new ArrayList<Post>();
		this.replyEligible = false;
		
	}
	
	public Post(int ID, int userID, int parentID, String userName,
			String topic, String message, LocalDate date) {
		this.ID = ID;
		this.userID = userID;
		this.parentID = parentID;
		this.userName = userName;
		this.topic = topic;
		this.message = message;
		this.date = date;
		this.replies = new ArrayList<Post>();
		this.replyEligible = false;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		this.ID = iD;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<Post> getReplies() {
		return replies;
	}
	
	public void addReply(Post reply) {
		this.replies.add(reply);
	}
	
	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
	public boolean isComment() {
		if( parentID > 0 ) {
			return false;
		}
		return true;
	}

	public boolean isReplyEligible() {
		return replyEligible;
	}

	public void setReplyEligible(boolean replyEligible) {
		this.replyEligible = replyEligible;
	}
	
	
}
