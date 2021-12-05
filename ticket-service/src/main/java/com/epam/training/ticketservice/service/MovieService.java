package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Movie;

import java.util.List;

public interface MovieService extends Observable{

   void createMovie(String title, String genre, int movieLengthMin);

   List<Movie> getMovies();

}
