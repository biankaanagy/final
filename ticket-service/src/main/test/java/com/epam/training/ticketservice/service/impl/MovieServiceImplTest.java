package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

class MovieServiceImplTest {

    @Mock
    private final MovieService movieService = new MovieServiceImpl();

    @Mock
    private List<Movie> MOVIES;

    @Test
    void testCreateMovieShouldAddMovieInformationsWhenCreatingNewMovies() {
        // Given
        String title = "Cim";
        String genre = "Mufaj";
        int movieLengthMin = 60;
        MOVIES = new ArrayList<>();

        // When
        MOVIES.add(Movie.builder().withTitle(title).withGenre(genre).withValue(movieLengthMin).build());

        // Then
        Assertions.assertEquals(MOVIES.get(0).getTitle(), title);
        Assertions.assertEquals(MOVIES.get(0).getGenre(), genre);
        Assertions.assertEquals(MOVIES.get(0).getMovieLengthMin(), movieLengthMin);
    }

}