package br.com.levieber.screenmatch.domain;

import br.com.levieber.screenmatch.application.mappers.EpisodeOmdbMapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        double rating;
        LocalDate releaseDate;
        try {
            rating = Double.parseDouble(episodeOmdb.rating());
        } catch (NumberFormatException e) {
            rating = 0;
        }
        try {
            releaseDate = LocalDate.parse(episodeOmdb.releaseDate());
        } catch (DateTimeParseException e) {
            releaseDate = null;
        }
        this.rating = rating;
        this.releaseDate = releaseDate;
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


