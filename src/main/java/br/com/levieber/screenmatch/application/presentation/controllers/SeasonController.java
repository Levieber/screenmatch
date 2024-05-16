package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.domain.entities.Season;
import br.com.levieber.screenmatch.domain.entities.Series;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class SeasonController extends BaseController {
    public List<Season> index(Series series) {
        List<Season> seasons = new ArrayList<>();
        Stream.iterate(1, i -> i + 1)
                .limit(series.getTotalSeasons())
                .map(i -> {
                    try {
                        return jsonMapper.map(apiClient.get(buildApiUrl(
                                "t=%s&season=%s",
                                series.getName(),
                                String.valueOf(i)
                        )), Season.class);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .forEach(seasons::add);
        return seasons;
    }
}
