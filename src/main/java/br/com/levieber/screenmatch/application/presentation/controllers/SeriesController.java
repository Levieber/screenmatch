package br.com.levieber.screenmatch.application.presentation.controllers;

import br.com.levieber.screenmatch.domain.Series;
import org.springframework.stereotype.Component;

@Component
public class SeriesController extends BaseController {
    public Series get(String seriesName) {
        String json = apiClient.get(buildApiUrl("t=%s", seriesName));
        return jsonMapper.map(json, Series.class);
    }
}
