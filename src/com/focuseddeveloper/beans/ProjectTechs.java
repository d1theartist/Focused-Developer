package com.focuseddeveloper.beans;

public class ProjectTechs {
	
	private String title;
	private String subtitle;
	private String summary;
	
	private String highlights[][];

	public ProjectTechs() {
		
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

	public String[][] getHighlights() {
		return highlights;
	}

	public void setHighlights(String[][] highlights) {
		this.highlights = highlights;
	}
	
	
	

}
