package net.dg.service;

import java.io.IOException;
import java.util.List;
import net.dg.model.DeathStats;
import net.dg.model.LocationStats;
import net.dg.model.RecoveryStats;


public interface CoronavirusService {

	List<LocationStats> getAllStats();
	List<DeathStats> getAllDeaths();
	List<RecoveryStats> getAllRecovery();
	void fetchVirusData() throws IOException, InterruptedException;
	void fetchDeathsData() throws IOException, InterruptedException;
	void fetchRecoveryData() throws IOException, InterruptedException;
		
}
