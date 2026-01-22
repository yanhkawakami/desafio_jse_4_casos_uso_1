package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    
    @Autowired
    MovieRepository repository;

    @Autowired
    GenreRepository genreRepository;


    public Page<MovieCardDTO> findAllMoviesPaged(String genreId, Pageable pageable) {

        Long formattedGenreId;
        if (!genreId.isEmpty()) {
            formattedGenreId = Long.parseLong(genreId);
        } else {
            formattedGenreId = null;
        }

        Page<Movie> movies = repository.findAllMoviesPaged(formattedGenreId, pageable);
        return movies.map(MovieCardDTO::new);
    }

    public MovieDetailsDTO findMovieDetails(Long movieId) {
        Optional<Movie> result = repository.findById(movieId);
        Movie movie = result.orElseThrow(() -> new ResourceNotFoundException("Filme com ID " + movieId + " não existe!"));
        MovieDetailsDTO dto = new MovieDetailsDTO(movie);

        // Busca o gênero no banco de dados
;       GenreDTO genreDTO = new GenreDTO(movie.getGenre());
        dto.setGenre(genreDTO);

        return dto;
    }
}
