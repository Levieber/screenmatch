package br.com.levieber.screenmatch.application.dtos;

import br.com.levieber.screenmatch.domain.entities.Series;
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
) {
   public static SeriesDto fromEntity(Series entity) {
       return new SeriesDto(
               entity.getId(),
               entity.getName(),
               entity.getTotalSeasons(),
               entity.getRating(),
               entity.getGenre(),
               entity.getActors(),
               entity.getPoster(),
               entity.getSynopsis()
       );
   }
}
