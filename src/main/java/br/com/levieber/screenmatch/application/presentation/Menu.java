package br.com.levieber.screenmatch.application.presentation;

import br.com.levieber.screenmatch.infra.JsonMapper;
import br.com.levieber.screenmatch.domain.Episode;
import br.com.levieber.screenmatch.domain.Season;
import br.com.levieber.screenmatch.domain.Series;
import br.com.levieber.screenmatch.infra.ApiClient;
import org.springframework.core.env.Environment;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiClient apiClient;
    private final JsonMapper jsonMapper;
    private final Environment env;

    public Menu(ApiClient apiClient, JsonMapper jsonMapper, Environment env) {
        this.apiClient = apiClient;
        this.jsonMapper = jsonMapper;
        this.env = env;
    }

    public void show() {
        System.out.println("Digite o nome da série:");
        String seriesName = URLEncoder.encode(scanner.nextLine(), StandardCharsets.UTF_8);

        String json = apiClient.get(buildApiUrl(seriesName));
        Series series = jsonMapper.map(json, Series.class);

        List<Season> seasons = new ArrayList<>(series.totalSeasons());

        Stream.iterate(1, i -> i + 1)
                .limit(series.totalSeasons())
                .map(i -> {
                    try {
                        return jsonMapper.map(apiClient.get(buildApiUrl(
                                "%s&season=%s".formatted(seriesName, i)
                        )), Season.class);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(seasons::add);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream().map(e -> new Episode(s.number(), e))).toList();

        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.printf("Top 5 episódios de %s:%n", series.name());
        episodes.stream()
                .sorted(Comparator.comparing(Episode::getRating).reversed())
                .limit(5)
                .forEach(e -> System.out.printf(
                        "Episódio #%d da temporada %d lançado em %s: %s%n",
                        e.getNumber(),
                        e.getSeason(),
                        dateTimeFormatter.format(e.getReleaseDate()),
                        e.getTitle()
                ));

        Map<Integer, Double> ratingPerSeason = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeason, Collectors.averagingDouble(Episode::getRating)));

        ratingPerSeason.forEach((key, value) -> System.out.printf("Temporada #%d: %f%n", key, value));

        DoubleSummaryStatistics ratingStatistics = episodes.stream()
                .filter(e -> e.getRating() > 0.0)
                .mapToDouble(Episode::getRating)
                .summaryStatistics();
        System.out.printf("Média: %f%n", ratingStatistics.getAverage());
        System.out.printf("Pior avaliação: %f%n", ratingStatistics.getMin());
        System.out.printf("Melhor avaliação: %f%n", ratingStatistics.getMax());
        System.out.printf("Quantidade de avaliações: %d%n", ratingStatistics.getCount());
    }

    private String buildApiUrl(String search) {
        return "https://omdbapi.com/?t=%s&apikey=%s".formatted(search, env.getProperty("api.key"));
    }
}
