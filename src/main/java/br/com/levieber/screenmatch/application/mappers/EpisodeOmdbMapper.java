package br.com.levieber.screenmatch.application.mappers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeOmdbMapper(
       @JsonAlias("Title") String title,
       @JsonAlias("Episode") int number,
       @JsonAlias("imdbRating") String rating,
       @JsonAlias("Released") String releaseDate
) {}
