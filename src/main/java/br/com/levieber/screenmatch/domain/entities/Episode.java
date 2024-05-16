package br.com.levieber.screenmatch.domain.entities;

import br.com.levieber.screenmatch.application.mappers.EpisodeOmdbMapper;

import java.time.LocalDate;
import java.util.Optional;
import java.util.OptionalDouble;

public class Episode {
    private final String title;
    private final int number;
    private final int season;
    private final double rating;
    private final LocalDate releaseDate;

    public Episode(int season, EpisodeOmdbMapper episodeOmdb) {
        this.season = season;
        this.title = episodeOmdb.title();
        this.number = episodeOmdb.number();
        this.rating = OptionalDouble.of(Double.parseDouble(episodeOmdb.rating())).orElse(0);
        this.releaseDate = Optional.of(LocalDate.parse(episodeOmdb.releaseDate())).orElse(null);
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public int getSeason() {
        return season;
    }

    public double getRating() {
        return rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "title=" + title +
                ", number=" + number +
                ", season=" + season +
                ", rating=" + rating +
                ", releaseDate=" + releaseDate +
                '}';
    }
}


