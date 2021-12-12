package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.service.exception.NoSuchMovieException;

import java.util.List;

public interface MovieService extends Observable{

   void deleteMovie(Movie movie);

   Movie createMovie(String title, String genre, int movieLengthMin);

   Movie updateMovie(String title, String genre, int movieLengthMin) throws NoSuchMovieException;

   Movie getMovie(String title);

   List<Movie> getAllMovie();

}
