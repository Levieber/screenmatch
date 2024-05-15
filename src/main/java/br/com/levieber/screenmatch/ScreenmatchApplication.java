package br.com.levieber.screenmatch;

import br.com.levieber.screenmatch.infra.JsonMapper;
import br.com.levieber.screenmatch.application.presentation.Menu;
import br.com.levieber.screenmatch.infra.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var apiClient = new ApiClient();
		var jsonMapper = new JsonMapper();
		var menu = new Menu(apiClient, jsonMapper, env);
		menu.show();
	}
}
