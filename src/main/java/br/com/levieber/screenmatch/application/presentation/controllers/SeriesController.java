package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.application.mappers.SeriesOmdbMapper;
import br.com.levieber.screenmatch.domain.entities.Series;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class SeriesController extends BaseController {
    List<Series> seriesList = new ArrayList<>();

    public List<Series> index() {
        return seriesList.stream().sorted(Comparator.comparing(Series::getGenre)).toList();
    }

    public Series get(String seriesName) {
        String json = apiClient.get(buildApiUrl("t=%s", seriesName));
        var seriesOmdb = jsonMapper.map(json, SeriesOmdbMapper.class);
        var series = new Series(seriesOmdb);
        seriesList.add(series);
        return series;
    }
}
