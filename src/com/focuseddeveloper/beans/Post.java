package com.focuseddeveloper.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
	
	public enum PostType { blogPost, comment };
	
	private int ID;
	private int userID;
	private String userName;
	private PostType postType;
	private String topic;
	private String message;
	private LocalDateTime date;
	ArrayList<Post> replies;

	public Post() {
		this.ID = 0;
		this.userID = 0;
		this.userName = "Name";
		this.postType = PostType.blogPost;
		this.topic = "Topic";
		this.message = "Post message";
		this.date = LocalDateTime.now();
		this.replies = new ArrayList<Post>();
		
	}
	
	public Post(int ID, int userID, String userName, PostType postType,
			String topic, String message, LocalDateTime date, ArrayList<Post> replies) {
		this.ID = ID;
		this.userID = userID;
		this.userName = userName;
		this.postType = postType;
		this.topic = topic;
		this.message = message;
		this.date = date;
		this.replies = replies;
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

	public PostType getPostType() {
		return postType;
	}

	public void setPostType(PostType postType) {
		this.postType = postType;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public ArrayList<Post> getReplies() {
		return replies;
	}
	
	public void addReply(Post reply) {
		this.replies.add(reply);
	}
	
	
}
