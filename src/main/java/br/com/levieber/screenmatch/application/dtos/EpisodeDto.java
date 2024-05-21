package br.com.levieber.screenmatch.application.dtos;

import br.com.levieber.screenmatch.domain.entities.Episode;

public record EpisodeDto(
        Long id,
        String title,
        int number,
        int season
) {
    public static EpisodeDto fromEntity(Episode entity) {
        return new EpisodeDto(entity.getId(), entity.getTitle(), entity.getNumber(), entity.getSeason());
    }
}
