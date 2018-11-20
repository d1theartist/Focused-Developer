package com.focuseddeveloper.beans;

public class Project {
	
	private String title;
	private String subtitle;
	private String summary;
	
	private String[][] keyFeatures = { {"Feature 1","This is Awesome"},{"Feature 2","Amazing!!!"},{"Feature 3","Wait... there's more!"} };
	
	
	
	public Project() {
		title = "Battleship";
		subtitle = "The classic board game";
		summary = "This is the game you know and love. The game is single player vs the CPU.";	
		
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


	public String[][] getKeyFeatures() {
		return keyFeatures;
	}


	public void setKeyFeatures(String[][] keyFeatures) {
		this.keyFeatures = keyFeatures;
	}

}
