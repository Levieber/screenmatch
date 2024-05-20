package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.application.dtos.SeriesDto;
import br.com.levieber.screenmatch.application.mappers.SeriesOmdbMapper;
import br.com.levieber.screenmatch.application.repositories.SeriesRepository;
import br.com.levieber.screenmatch.domain.entities.Series;
import br.com.levieber.screenmatch.domain.enums.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SeriesController extends BaseController {
    @Autowired
    SeriesRepository seriesRepository;

    @GetMapping("/series")
    public List<SeriesDto> index() {
        return seriesRepository.findAll()
                .stream()
                .map(s ->
                    new SeriesDto(
                            s.getId(),
                            s.getName(),
                            s.getTotalSeasons(),
                            s.getRating(),
                            s.getGenre(),
                            s.getActors(),
                            s.getPoster(),
                            s.getSynopsis()
                    )
                )
                .toList();
    }

    public Series get(String seriesName) {
        String json = apiClient.get(buildApiUrl("t=%s", seriesName));
        var seriesOmdb = jsonMapper.map(json, SeriesOmdbMapper.class);
        var series = new Series(seriesOmdb);
        seriesRepository.save(series);
        return series;
    }

    public Optional<Series> findByName(String seriesName) {
        return seriesRepository.findFirstByNameContainingIgnoreCase(seriesName);
    }

    public List<Series> findByActor(String actorName, double rating) {
        return seriesRepository.findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(actorName, rating);
    }

    @GetMapping("/series/top5")
    public List<SeriesDto> findTop5() {
        return seriesRepository.findTop5ByOrderByRatingDesc()
                .stream()
                .map(s ->
                        new SeriesDto(
                                s.getId(),
                                s.getName(),
                                s.getTotalSeasons(),
                                s.getRating(),
                                s.getGenre(),
                                s.getActors(),
                                s.getPoster(),
                                s.getSynopsis()
                        )
                )
                .toList();
    }

    public List<Series> findByGenre(String genre) {
        return seriesRepository.findByGenre(Genre.fromPortugueseString(genre));
    }

    public List<Series> findShort(int maxSeasons, double minRating) {
        return seriesRepository.findBySeasonAndRating(maxSeasons, minRating);
    }
}
