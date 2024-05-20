package br.com.levieber.screenmatch.application.dtos;

import br.com.levieber.screenmatch.domain.enums.Genre;

public record SeriesDto(
        Long id,
        String name,
        int totalSeasons,
        double rating,
        Genre genre,
        String actors,
        String poster,
        String synopsis
) {}
