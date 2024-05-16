package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.domain.Season;
import br.com.levieber.screenmatch.domain.Series;
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
                .limit(series.totalSeasons())
                .map(i -> {
                    try {
                        return jsonMapper.map(apiClient.get(buildApiUrl(
                                "t=%s&season=%s",
                                series.name(),
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
