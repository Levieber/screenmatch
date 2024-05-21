package br.com.levieber.screenmatch.application.services;

import br.com.levieber.screenmatch.application.dtos.EpisodeDto;
import br.com.levieber.screenmatch.application.dtos.SeriesDto;
import br.com.levieber.screenmatch.application.repositories.SeriesRepository;
import br.com.levieber.screenmatch.domain.enums.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {
    @Autowired
    SeriesRepository seriesRepository;

    public List<SeriesDto> getAll() {
        return seriesRepository.findAll()
                .stream()
                .map(SeriesDto::fromEntity)
                .toList();
    }

    public List<SeriesDto> getTop5() {
        return seriesRepository.findTop5ByOrderByRatingDesc()
                .stream()
                .map(SeriesDto::fromEntity)
                .toList();
    }

    public List<SeriesDto> getNews() {
        return seriesRepository.findSeriesWithNewestEpisodes()
                .stream()
                .map(SeriesDto::fromEntity)
                .toList();
    }

    public Optional<SeriesDto> getById(Long id) {
        var series = seriesRepository.findById(id);
        return series.map(SeriesDto::fromEntity);
    }

    public List<EpisodeDto> getSeasonsFromSeries(Long id) {
        var series = seriesRepository.findById(id);
        return series
                .map(value -> value.getEpisodes().stream().map(EpisodeDto::fromEntity).toList())
                .orElse(null);
    }

    public List<EpisodeDto> getSeasonEpisodes(Long seriesId, Long seasonId) {
        return seriesRepository.findEpisodesBySeriesSeason(seriesId, seasonId)
                .stream()
                .map(EpisodeDto::fromEntity)
                .toList();
    }


    public List<EpisodeDto> getTop5EpisodesFromSeries(Long seriesId) {
        return seriesRepository.findTop5EpisodesFromSeries(seriesId)
                .stream()
                .map(EpisodeDto::fromEntity)
                .toList();
    }

    public List<SeriesDto> getSeriesByGenre(String genre) {
        return seriesRepository.findSeriesByGenre(Genre.fromPortugueseString(genre))
                .stream()
                .map(SeriesDto::fromEntity).toList();
    }
}
