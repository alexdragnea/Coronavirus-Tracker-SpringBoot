package net.dg.model;

public class RecoveryStats {

	private String state;
	private String country;
	private int totalRecoveries;
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
	
	public int getTotalRecoveries() {
		return totalRecoveries;
	}
	
	public void setTotalRecoveries(int totalDeaths) {
		this.totalRecoveries = totalDeaths;
	}
	
	public int getChangesFromPrevDay() {
		return changesFromPrevDay;
	}
	
	public void setChangesFromPrevDay(int changesFromPrevDay) {
		this.changesFromPrevDay = changesFromPrevDay;
	}
	
	
}
