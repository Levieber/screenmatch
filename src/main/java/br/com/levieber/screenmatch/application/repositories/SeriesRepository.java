package br.com.levieber.screenmatch.application.repositories;

import br.com.levieber.screenmatch.domain.entities.Episode;
import br.com.levieber.screenmatch.domain.entities.Series;
import br.com.levieber.screenmatch.domain.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findFirstByNameContainingIgnoreCase(String name);
    List<Series> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, double rating);
    List<Series> findTop5ByOrderByRatingDesc();
    List<Series> findByGenre(Genre genre);
    @Query("SELECT s FROM Series s WHERE s.totalSeasons <= :maxSeasons AND s.rating >= :minRating")
    List<Series> findBySeasonAndRating(int maxSeasons, double minRating);
    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title ILIKE %:episodeTitle%")
    List<Episode> findEpisodeByTitle(String episodeTitle);
    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series ORDER BY e.rating DESC LIMIT 5")
    List<Episode> findTop5EpisodesFromSeries(Series series);
    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series AND YEAR(e.releaseDate) >= :releaseYear")
    List<Episode> findEpisodeByAfterReleaseYearFromSeries(int releaseYear, Series series);
}
