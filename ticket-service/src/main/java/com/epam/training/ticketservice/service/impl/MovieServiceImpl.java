package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.Observer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private List<Movie> MOVIES;

    public MovieServiceImpl() {
        MOVIES = new ArrayList<>();
    }

    @Override
    public void createMovie(String title, String genre, int movieLengthMin) {
        MOVIES.add(Movie.builder().withTitle(title).withGenre(genre).withValue(movieLengthMin).build());
    }

    @Override
    public List<Movie> getMovies() {
        return new ArrayList<>(MOVIES);
    }

    @Override
    public void subscribe(Observer observer) {}

}
