package br.com.levieber.screenmatch.application.mappers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesOmdbMapper(
        @JsonAlias("Title") String name,
        @JsonAlias("totalSeasons") int totalSeasons,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Genre") String genre,
        @JsonAlias("Actors") String actors,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Plot") String synopsis
) {}

