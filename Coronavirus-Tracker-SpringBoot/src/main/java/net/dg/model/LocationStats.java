package net.dg.model;

public class LocationStats {

	private String state;
	private String country;
	private int totalCases;
	private int changesFromPrevDay;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getChangesFromPrevDay() {
		return changesFromPrevDay;
	}
	public void setChangesFromPrevDay(int changesFromPrevDay) {
		this.changesFromPrevDay = changesFromPrevDay;
	}
	
	
}
