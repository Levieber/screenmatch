package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.application.repositories.SeriesRepository;
import br.com.levieber.screenmatch.domain.entities.Episode;
import br.com.levieber.screenmatch.domain.entities.Season;
import br.com.levieber.screenmatch.domain.entities.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class EpisodeController extends BaseController {
    @Autowired
    private SeriesRepository seriesRepository;

    public Optional<Series> index(String seriesName) {
        Optional<Series> series = seriesRepository.findByNameContainingIgnoreCase(seriesName);

        if (series.isEmpty()) {
            System.out.println("Série não encontrada!");
            return Optional.empty();
        }

        var chosenSeries = series.get();

        List<Episode> episodes = Stream.iterate(1, i -> i + 1)
                .limit(chosenSeries.getTotalSeasons())
                .map(i -> {
                    try {
                        return jsonMapper.map(apiClient.get(buildApiUrl(
                                "t=%s&season=%s",
                                chosenSeries.getName(),
                                String.valueOf(i)
                        )), Season.class);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .flatMap(s -> s.episodes().stream().map(e -> new Episode(s.number(), e)))
                .toList();

        chosenSeries.setEpisodes(episodes);
        seriesRepository.save(chosenSeries);
        return Optional.of(chosenSeries);
    }
}
