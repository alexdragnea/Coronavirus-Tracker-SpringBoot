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
import net.dg.model.DeathStats;
import net.dg.model.LocationStats;
import net.dg.model.RecoveryStats;


@Service
public class CoronavirusServiceImpl implements CoronavirusService{

	private static String URL_DATA_CASES = 
			"https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

	private static String URL_DATA_DEATHS = 
			"https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";

	private static String URL_DATA_RECOVERY = 
			"https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

	private List<LocationStats> allStats = new ArrayList<>();

	private List<DeathStats> allDeaths = new ArrayList<>();

	private List<RecoveryStats> allRecovery = new ArrayList<>();

	@Override
	public List<LocationStats> getAllStats() {

		return allStats;
	}

	@Override
	public List<DeathStats> getAllDeaths() {
		// TODO Auto-generated method stub
		return allDeaths;
	}

	@Override
	public List<RecoveryStats> getAllRecovery() {
		// TODO Auto-generated method stub
		return allRecovery;
	}


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	@Override
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL_DATA_CASES))
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


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	@Override
	public void fetchDeathsData() throws IOException, InterruptedException {
		List<DeathStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL_DATA_DEATHS))
				.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			DeathStats deathStats = new DeathStats();
			deathStats.setState(record.get("Province/State"));
			deathStats.setCountry(record.get("Country/Region"));
			int totalDeaths = Integer.parseInt(record.get(record.size() - 1));
			int changesFromPrevDay = Integer.parseInt(record.get(record.size() - 2));
			deathStats.setTotalDeaths(totalDeaths);
			deathStats.setChangesFromPrevDay(totalDeaths - changesFromPrevDay);
			newStats.add(deathStats);
		}
		this.allDeaths = newStats;

	}


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	@Override
	public void fetchRecoveryData() throws IOException, InterruptedException {
		List<RecoveryStats> newStats = new ArrayList<>();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL_DATA_RECOVERY))
				.build();
		HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			RecoveryStats recoveryStats = new RecoveryStats();
			recoveryStats.setState(record.get("Province/State"));
			recoveryStats.setCountry(record.get("Country/Region"));
			int totalRecovery = Integer.parseInt(record.get(record.size() - 1));
			int changesFromPrevDay = Integer.parseInt(record.get(record.size() - 2));
			recoveryStats.setTotalRecoveries(totalRecovery);
			recoveryStats.setChangesFromPrevDay(totalRecovery - changesFromPrevDay);
			newStats.add(recoveryStats);
		}
		this.allRecovery = newStats;

	}



}
