package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.application.mappers.SeriesOmdbMapper;
import br.com.levieber.screenmatch.application.repositories.SeriesRepository;
import br.com.levieber.screenmatch.domain.entities.Series;
import br.com.levieber.screenmatch.domain.enums.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class SeriesController extends BaseController {
    @Autowired
    SeriesRepository seriesRepository;

    public List<Series> index() {
        return seriesRepository.findAll().stream().sorted(Comparator.comparing(Series::getGenre)).toList();
    }

    public Series get(String seriesName) {
        String json = apiClient.get(buildApiUrl("t=%s", seriesName));
        var seriesOmdb = jsonMapper.map(json, SeriesOmdbMapper.class);
        var series = new Series(seriesOmdb);
        seriesRepository.save(series);
        return series;
    }

    public Optional<Series> findByName(String seriesName) {
        return seriesRepository.findByNameContainingIgnoreCase(seriesName);
    }

    public List<Series> findByActor(String actorName, double rating) {
        return seriesRepository.findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(actorName, rating);
    }

    public List<Series> findTop5() {
        return seriesRepository.findTop5ByOrderByRatingDesc();
    }

    public List<Series> findByGenre(String genre) {
        return seriesRepository.findByGenre(Genre.fromPortugueseString(genre));
    }

    public List<Series> findShort(int maxSeasons, double rating) {
        return seriesRepository.findByTotalSeasonsLessThanEqualAndRatingGreaterThanEqual(maxSeasons, rating);
    }
}
