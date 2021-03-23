package net.dg.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dg.model.LocationStats;


public interface CoronavirusService {

	List<LocationStats> getAllStats();
	void fetchVirusData() throws IOException, InterruptedException;
}
