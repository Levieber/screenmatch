package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.application.dtos.EpisodeDto;
import br.com.levieber.screenmatch.application.dtos.SeriesDto;
import br.com.levieber.screenmatch.application.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/series")
public class SeriesController extends BaseController {
   @Autowired
   private SeriesService seriesService;

    @GetMapping
    public List<SeriesDto> index() {
        return seriesService.getAll();
    }

    @GetMapping("/top5")
    public List<SeriesDto> showTop5() {
        return seriesService.getTop5();
    }

    @GetMapping("/news")
    public List<SeriesDto> showNews() {
        return seriesService.getNews();
    }

    @GetMapping("/{id}")
    public Optional<SeriesDto> show(@PathVariable Long id) {
        return seriesService.getById(id);
    }

    @GetMapping("/{id}/seasons/all")
    public List<EpisodeDto> showSeasons(@PathVariable Long id) {
        return seriesService.getSeasonsFromSeries(id);
    }

    @GetMapping("/{seriesId}/seasons/{seasonId}")
    public List<EpisodeDto> showSeasonEpisodes(@PathVariable Long seriesId, @PathVariable Long seasonId) {
        return seriesService.getSeasonEpisodes(seriesId, seasonId);
    }

    @GetMapping("/{seriesId}/seasons/top5")
    public List<EpisodeDto> showTop5EpisodesFromSeries(@PathVariable Long seriesId) {
        return seriesService.getTop5EpisodesFromSeries(seriesId);
    }

    @GetMapping("/genre/{genreId}")
    public List<SeriesDto> showSeriesByGenre(@PathVariable String genreId) {
        return seriesService.getSeriesByGenre(genreId);
    }
}
