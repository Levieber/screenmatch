package br.com.levieber.screenmatch.application.repositories;

import br.com.levieber.screenmatch.domain.entities.Episode;
import br.com.levieber.screenmatch.domain.entities.Series;
import br.com.levieber.screenmatch.domain.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findTop5ByOrderByRatingDesc();
    @Query("SELECT s FROM Series s JOIN s.episodes e GROUP BY s ORDER BY MAX(e.releaseDate) DESC LIMIT 5")
    List<Series> findSeriesWithNewestEpisodes();
    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s.id = :seriesId ORDER BY e.rating DESC LIMIT 5")
    List<Episode> findTop5EpisodesFromSeries(Long seriesId);
    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s.id = :seriesId AND e.season = :seasonId")
    List<Episode> findEpisodesBySeriesSeason(Long seriesId, Long seasonId);
    @Query("SELECT s FROM Series s WHERE s.genre = :genre")
    List<Series> findSeriesByGenre(Genre genre);
}
