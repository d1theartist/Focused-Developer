package com.focuseddeveloper.beans;

import java.util.ArrayList;

public class Project {
	
	private String primaryProjectTech;
	private String title;
	private String subtitle;
	private String summary;
	
	private String urlString;
	
	private ArrayList<String> tags = new ArrayList<String>();
	
	//private String[][] keyFeatures = { {"Feature 1","This is Awesome"},{"Feature 2","Amazing!!!"},{"Feature 3","Wait... there's more!"} };
	private ArrayList<String[]> keyFeatures = new ArrayList<String[]>(); 
	
	
	public Project() {
		primaryProjectTech = "C++";
		title = "Battleship";
		subtitle = "The classic board game";
		summary = "This is the game you know and love. The game is single player vs the CPU.";	
		
		keyFeatures.add(new String[] {"Feature 1","This is Awesome"});
		keyFeatures.add(new String[] {"Feature 2","Amazing!!!"});
		keyFeatures.add(new String[] {"Feature 3","Wait... there's even more!"});
		
		urlString = "https://github.com/";
		tags.add("Language 1");
		tags.add("tools 1");
		tags.add("tools 2");
	}
	
	public Project(String primaryProjectTech, String title, String subtitle, String summary, ArrayList<String[]> keyFeatures, String urlString, ArrayList<String> tags) {
		this.primaryProjectTech = primaryProjectTech;
		this.title = title;
		this.subtitle = subtitle;
		this.summary = summary;
		
		this.keyFeatures = keyFeatures;
		this.urlString = urlString;
		this.tags = tags;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}


	public ArrayList<String[]> getKeyFeatures() {
		return keyFeatures;
	}


	public void setKeyFeatures(ArrayList<String[]> keyFeatures) {
		this.keyFeatures = keyFeatures;
	}
	
	public void addKeyFeature(String[] feature) {
		keyFeatures.add(feature);
	}

	public String getPrimaryProjectTech() {
		return primaryProjectTech;
	}

	public void setPrimaryProjectTech(String primaryProjectTech) {
		this.primaryProjectTech = primaryProjectTech;
	}

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}

}
