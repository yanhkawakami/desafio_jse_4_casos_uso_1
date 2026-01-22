package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService service;

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> getAllMoviesPaged (
            @RequestParam (required = false, defaultValue = "") String genreId,
            Pageable pageable){
        Page<MovieCardDTO> movies = service.findAllMoviesPaged(genreId, pageable);
        return ResponseEntity.ok(movies);
    }

    @PreAuthorize("hasAnyRole('VISITOR', 'MEMBER')")
    @GetMapping(value = "/{movieId}")
    public ResponseEntity<MovieDetailsDTO> getMovieDetails (@PathVariable Long movieId) {
        MovieDetailsDTO result = service.findMovieDetails(movieId);
        return ResponseEntity.ok(result);
    }

}
