package br.com.levieber.screenmatch.application.repositories;

import br.com.levieber.screenmatch.domain.entities.Series;
import br.com.levieber.screenmatch.domain.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByNameContainingIgnoreCase(String name);
    List<Series> findByActorsContainingIgnoreCaseAndRatingGreaterThanEqual(String actorName, double rating);
    List<Series> findTop5ByOrderByRatingDesc();
    List<Series> findByGenre(Genre genre);
    List<Series> findByTotalSeasonsLessThanEqualAndRatingGreaterThanEqual(int maxSeasons, double rating);
}
