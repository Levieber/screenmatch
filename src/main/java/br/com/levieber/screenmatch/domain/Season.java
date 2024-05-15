package br.com.levieber.screenmatch.domain;

import br.com.levieber.screenmatch.application.mappers.EpisodeOmdbMapper;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Season(
        @JsonAlias("Season") int number,
        @JsonAlias("Episodes") List<EpisodeOmdbMapper> episodes
) {}
