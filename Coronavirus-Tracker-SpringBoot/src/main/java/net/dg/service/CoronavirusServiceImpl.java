package net.dg.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.dg.model.LocationStats;

@Service
public class CoronavirusServiceImpl implements CoronavirusService{

	private static String URL_DATA = 
			"https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allStats = new ArrayList<>();
	
	@Override
	public List<LocationStats> getAllStats() {

		return allStats;
	}

	@PostConstruct
    @Scheduled(cron = "* * 1 * * *")
	@Override
	public void fetchVirusData() throws IOException, InterruptedException {
		 List<LocationStats> newStats = new ArrayList<>();
	        HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(URL_DATA))
	                .build();
	        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
	        StringReader csvBodyReader = new StringReader(httpResponse.body());
	        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
	        for (CSVRecord record : records) {
	            LocationStats locationStat = new LocationStats();
	            locationStat.setState(record.get("Province/State"));
	            locationStat.setCountry(record.get("Country/Region"));
	            int totalCases = Integer.parseInt(record.get(record.size() - 1));
	            int changesFromPrevDay = Integer.parseInt(record.get(record.size() - 2));
	            locationStat.setTotalCases(totalCases);
	            locationStat.setChangesFromPrevDay(totalCases - changesFromPrevDay);
	            newStats.add(locationStat);
	        }
	        this.allStats = newStats;
		
	}

}
