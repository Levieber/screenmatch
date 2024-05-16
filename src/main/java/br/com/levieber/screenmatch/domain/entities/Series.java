package br.com.levieber.screenmatch.domain.entities;

import br.com.levieber.screenmatch.application.mappers.SeriesOmdbMapper;
import br.com.levieber.screenmatch.application.services.TranslateService;
import br.com.levieber.screenmatch.domain.enums.Genre;

import java.util.OptionalDouble;

public class Series {
    private final String name;
    private final int totalSeasons;
    private final double rating;
    private final Genre genre;
    private final String actors;
    private final String poster;
    private final String synopsis;

    public Series(SeriesOmdbMapper seriesOmdb) {
        this.name = seriesOmdb.name();
        this.totalSeasons = seriesOmdb.totalSeasons();
        this.rating = OptionalDouble.of(Double.parseDouble(seriesOmdb.rating())).orElse(0);
        this.genre = Genre.fromString(seriesOmdb.genre().split(",")[0].trim());
        this.actors = seriesOmdb.actors();
        this.poster = seriesOmdb.poster();
        this.synopsis = TranslateService.fromEnglishToPortuguese(seriesOmdb.synopsis()).trim();
    }

    public String getName() {
        return name;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public double getRating() {
        return rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getActors() {
        return actors;
    }

    public String getPoster() {
        return poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    @Override
    public String toString() {
        return "Series{" +
                "genre=" + genre +
                ", name=" + name +
                ", totalSeasons=" + totalSeasons +
                ", rating=" + rating +
                ", actors=" + actors +
                ", poster=" + poster +
                ", synopsis=" + synopsis +
                '}';
}
}
