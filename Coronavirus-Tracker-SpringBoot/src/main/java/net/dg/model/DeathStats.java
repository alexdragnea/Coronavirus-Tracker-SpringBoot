package net.dg.model;

public class DeathStats {

	private String state;
	private String country;
	private int totalDeaths;
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
	
	public int getTotalDeaths() {
		return totalDeaths;
	}
	
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	
	public int getChangesFromPrevDay() {
		return changesFromPrevDay;
	}
	
	public void setChangesFromPrevDay(int changesFromPrevDay) {
		this.changesFromPrevDay = changesFromPrevDay;
	}
	
	
}
