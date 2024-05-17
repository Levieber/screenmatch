package br.com.levieber.screenmatch.application.repositories;

import br.com.levieber.screenmatch.domain.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {}
