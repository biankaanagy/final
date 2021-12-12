package com.epam.training.ticketservice.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.exception.NoSuchMovieException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class MovieServiceImplTest {

    private static final String TEST_TITLE = "title";
    private static final String TEST_GENRE = "genre";
    private static final int TEST_LENGTH = 60;
    private static final String TEST_UPDATE_GENRE = "updated genre";
    private static final int TEST_UPDATE_LENGTH = 30;

    @InjectMocks
    private MovieServiceImpl underTest;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMovieShouldSaveMovie(){
        //Given
        Movie expected = new Movie(TEST_TITLE, TEST_GENRE,TEST_LENGTH);
        given(movieRepository.save(expected)).willReturn(expected);

        //When
        Movie actual = underTest.createMovie(TEST_TITLE, TEST_GENRE, TEST_LENGTH);

        //Then
        assertEquals(expected, actual);
        then(movieRepository).should().save(expected);
    }


    @Test
    public void testUpdateMovieShouldUpdateMovieWhenPresent() throws NoSuchMovieException {
        //Given
        Movie movie = new Movie(TEST_TITLE, TEST_GENRE,TEST_LENGTH);
        given(movieRepository.findByTitle(TEST_TITLE)).willReturn(Optional.of(movie));

        Movie expected = new Movie(TEST_TITLE, TEST_UPDATE_GENRE, TEST_UPDATE_LENGTH);
        given(movieRepository.save(expected)).willReturn(expected);

        //When
        Movie actual = underTest.updateMovie(TEST_TITLE, TEST_UPDATE_GENRE, TEST_UPDATE_LENGTH);

        //Then
        assertEquals(expected, actual);
        then(movieRepository).should().findByTitle(TEST_TITLE);
        then(movieRepository).should().save(expected);
    }

    @Test
    public void testUpdateMovieShouldThrowExceptionWhenMovieNotPresent() throws NoSuchMovieException {
        //Given
        Movie expected = new Movie(TEST_TITLE, TEST_GENRE,TEST_LENGTH);
        given(movieRepository.findByTitle(TEST_TITLE)).willReturn(Optional.empty());

        //When
        Assertions.assertThrows(NoSuchMovieException.class, ()-> {
            underTest.updateMovie(TEST_TITLE, TEST_GENRE, TEST_LENGTH);
        });

        //Then
        then(movieRepository).should().findByTitle(TEST_TITLE);
        then(movieRepository).should(times(0)).save(any());
    }

    @Test
    public void testDeleteMovieShouldDeleteMovie(){
        //Given
        Movie expected = new Movie(TEST_TITLE, TEST_GENRE, TEST_LENGTH);

        //When
        underTest.deleteMovie(expected);

        //Then
        then(movieRepository).should().delete(expected);
    }

    @Test
    public void testGetMovieShouldFindMovie(){
        //Given
        Movie expected = new Movie(TEST_TITLE, TEST_GENRE, TEST_LENGTH);
        given(movieRepository.findByTitle(TEST_TITLE)).willReturn(Optional.of(expected));

        //When
        Movie actual = underTest.getMovie(TEST_TITLE);

        //Then
        assertEquals(expected, actual);
        then(movieRepository).should().findByTitle(TEST_TITLE);
    }

    @Test
    public void testGetAllMovieShouldFindAllMovie(){
        //Given
        Movie movie = new Movie(TEST_TITLE, TEST_GENRE, TEST_LENGTH);
        List<Movie> expected = List.of(movie);
        given(movieRepository.findAll()).willReturn(expected);

        //When
        List<Movie> actual = underTest.getAllMovie();

        //Then
        assertEquals(expected, actual);
        then(movieRepository).should().findAll();
    }

}