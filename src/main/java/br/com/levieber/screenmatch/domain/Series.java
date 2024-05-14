package br.com.levieber.screenmatch.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Series(
        @JsonAlias("Title") String name,
        @JsonAlias("totalSeasons") int totalSeasons,
        @JsonAlias("imdbRating") String rating
) {}

