package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.impl.AccountLoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class MovieCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieCommandHandler.class);

    private final MovieService movieService;

    private List<Movie> movies;

    public MovieCommandHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(value = "Movie creator", key = "create movie")
    public void createMovie(String title, String genre, int movieLengthMin) {
        movieService.createMovie(title, genre, movieLengthMin);
        LOGGER.info("Created movie "+title+" "+genre+" "+movieLengthMin);
    }

    @ShellMethod(value = "Movies list", key = "list movies")
    public void listMovies() {
        movies = movieService.getMovies();
        for (int i = 0; i < movies.size(); i++) {
            LOGGER.info(movies.get(i).toString());
        }
    }

    @ShellMethod(value = "Movie updator", key = "update movie")
    public void updateMovie() {

    }
}
