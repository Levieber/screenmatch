package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.infra.api.ApiClient;
import br.com.levieber.screenmatch.infra.mappers.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
public abstract class BaseController {
    protected final ApiClient apiClient = new ApiClient();
    protected final JsonMapper jsonMapper = new JsonMapper();

    @Autowired
    private Environment env;

    protected String buildApiUrl(String format, String... args) {
        return "https://omdbapi.com/?%s&apikey=%s".formatted(
                format.formatted(
                        Arrays.stream(args).map(a -> URLEncoder.encode(a, StandardCharsets.UTF_8)).toArray()
                ),
                env.getProperty("api.key")
        );
    }
}
