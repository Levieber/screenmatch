package br.com.levieber.screenmatch;

import br.com.levieber.screenmatch.application.mappers.JsonMapper;
import br.com.levieber.screenmatch.domain.Series;
import br.com.levieber.screenmatch.infra.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.http.HttpClient;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var apiUrl = "https://www.omdbapi.com/?t=gilmore+girls&apikey=%s".formatted(env.getProperty("api.key"));
		var apiClient = new ApiClient();
		var json = apiClient.get(apiUrl);
		var jsonMapper = new JsonMapper();
		var series = jsonMapper.map(json, Series.class);
		System.out.println(series);
	}
}
