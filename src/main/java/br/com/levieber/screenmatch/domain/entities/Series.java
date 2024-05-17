package br.com.levieber.screenmatch.domain.entities;

import br.com.levieber.screenmatch.application.mappers.SeriesOmdbMapper;
import br.com.levieber.screenmatch.application.services.TranslateService;
import br.com.levieber.screenmatch.domain.enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int totalSeasons;
    private double rating;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String actors;
    private String poster;
    private String synopsis;
    @Transient
    private List<Episode> episodes = new ArrayList<>();

    public Series(SeriesOmdbMapper seriesOmdb) {
        this.name = seriesOmdb.name();
        this.totalSeasons = seriesOmdb.totalSeasons();
        this.rating = OptionalDouble.of(Double.parseDouble(seriesOmdb.rating())).orElse(0);
        this.genre = Genre.fromString(seriesOmdb.genre().split(",")[0].trim());
        this.actors = seriesOmdb.actors();
        this.poster = seriesOmdb.poster();
        this.synopsis = TranslateService.fromEnglishToPortuguese(seriesOmdb.synopsis()).trim();
    }

    public Series() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
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
