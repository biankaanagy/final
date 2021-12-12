package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.Observer;
import com.epam.training.ticketservice.service.exception.NoSuchMovieException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(String title, String genre, int movieLengthMin) {
        Movie movie = Movie.builder().withTitle(title).withGenre(genre).withValue(movieLengthMin).build();
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(String title, String genre, int movieLengthMin) throws NoSuchMovieException {
        Optional<Movie> optionalMovie = movieRepository.findByTitle(title);
        if (optionalMovie.isEmpty()){
            throw new NoSuchMovieException();
        }
        Movie movie = optionalMovie.get();
        movie.setGenre(genre);
        movie.setMovieLengthMin(movieLengthMin);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public Movie getMovie(String movieTitle) {
        return movieRepository.findByTitle(movieTitle).get();
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public void subscribe(Observer observer) {}

}
