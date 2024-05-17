package br.com.levieber.screenmatch.domain.entities;

import br.com.levieber.screenmatch.application.mappers.EpisodeOmdbMapper;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int number;
    private int season;
    private double rating;
    private LocalDate releaseDate;
    @ManyToOne
    private Series series;

    public Episode(int season, EpisodeOmdbMapper episodeOmdb) {
        this.season = season;
        this.title = episodeOmdb.title();
        this.number = episodeOmdb.number();
        try {
            this.rating = Double.parseDouble(episodeOmdb.rating());
        } catch (NumberFormatException e) {
            this.rating = 0;
        }
        try {
            this.releaseDate = LocalDate.parse(episodeOmdb.releaseDate());
        } catch (DateTimeParseException e) {
            this.releaseDate = null;
        }
    }

    public Episode() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
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


