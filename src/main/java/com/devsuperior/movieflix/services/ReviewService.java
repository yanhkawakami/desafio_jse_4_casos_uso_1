package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository repository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    AuthService authService;

    public ReviewDTO saveReview (ReviewDTO dto) {
        Review entity = new Review();

        Long movieId = dto.getMovieId();
        Optional<Movie> optMovie = movieRepository.findById(movieId);

        Movie movie = optMovie.orElseThrow(() -> new ResourceNotFoundException("Filme com id " + movieId));
        User user = authService.authenticated();

        entity.setText(dto.getText());
        entity.setMovie(movie);
        entity.setUser(user);

        entity = repository.save(entity);

        return new ReviewDTO(entity);
    }
}
