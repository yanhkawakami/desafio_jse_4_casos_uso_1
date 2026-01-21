package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MovieService {
    
    @Autowired
    MovieRepository repository;


    public Page<MovieCardDTO> findAllMoviesPaged(Pageable pageable) {
        Page<Movie> movies = repository.findAll(pageable);
        return movies.map(MovieCardDTO::new);
    }
}
