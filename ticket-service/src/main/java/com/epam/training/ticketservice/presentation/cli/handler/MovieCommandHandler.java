package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.exception.NoSuchMovieException;
import com.epam.training.ticketservice.service.impl.AccountLoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class MovieCommandHandler {

    private final MovieService movieService;
    private final AccountLoginService accountLoginService;

    private List<Movie> movies;

    public MovieCommandHandler(MovieService movieService, AccountLoginService accountLoginService) {
        this.movieService = movieService;
        this.accountLoginService = accountLoginService;
    }

    @ShellMethod(value = "Movie creator", key = "create movie")
    public String createMovie(String title, String genre, int movieLengthMin) {
        if (accountLoginService.accountLoggedIn()) {
            movieService.createMovie(title, genre, movieLengthMin);
            return "Created movie " + title + " " + genre + " " + movieLengthMin;
        }
        return "You are not admin.";
    }

    @ShellMethod(value = "Movies list", key = "list movies")
    public String listMovies() {
        movies = movieService.getAllMovie();
        if (!movies.isEmpty()) {
            StringBuilder list = new StringBuilder();
            for (Movie m : movies) {
                list.append(m.getTitle() + " (" + m.getGenre() + " " + m.getMovieLengthMin() + " minutes)\n");
            }
            return list + "";
        }
        return "There are no movies at the moment";
    }

    @ShellMethod(value = "Movie updator", key = "update movie")
    public String updateMovie(String title, String genre, Integer length) {
        String result = "You are not admin";
        if (accountLoginService.accountLoggedIn()) {
            try {
                movieService.updateMovie(title, genre, length);
                result = title + " movie updateed.";
            } catch (NoSuchMovieException e) {
                result = "No such movie: " + title;
            }
        }
        return result;
    }

    @ShellMethod(value = "Movie deleter", key = "delete movie")
    public String deleteMovie(String title) {
        String result = "You are not admin";
        if (accountLoginService.accountLoggedIn()) {
            try {
                Movie deleteMovie = movieService.getMovie(title);
                movieService.deleteMovie(deleteMovie);
                result = deleteMovie.getTitle() + " movie deleted";
            } catch (NullPointerException e) {
                result = "No such movie: " + title;
            }
        }
        return result;
    }
}