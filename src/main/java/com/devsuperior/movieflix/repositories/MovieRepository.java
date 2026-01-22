package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre "
            + "WHERE (:genreId IS NULL OR obj.genre.id = :genreId) "
            + "ORDER BY obj.title ASC")
    Page<Movie> findAllMoviesPaged(Long genreId, Pageable pageable);
}
