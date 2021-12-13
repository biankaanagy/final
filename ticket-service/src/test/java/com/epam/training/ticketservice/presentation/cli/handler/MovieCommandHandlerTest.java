package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

class MovieCommandHandlerTest {

    private static final String TEST_TITLE = "title";
    private static final String TEST_GENRE = "genre";
    private static final String TEST_LENGTH = "1";
    private static final int TEST_INT_LENGTH = 1;

    @InjectMocks
    private static MovieCommandHandler underTest;

    @Mock
    private MovieService movieService;

    @Mock
    private AccountLoginService accountLoginService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMovieShouldSuccessfullyCreatedMovie(){
        //Given
        String expected = "Created movie " + TEST_TITLE + " " + TEST_GENRE + " " + TEST_LENGTH;
        Movie movie = new Movie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);
        given(accountLoginService.accountLoggedIn()).willReturn(true);
        given(movieService.createMovie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH)).willReturn(movie);

        //When
        String actual = underTest.createMovie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
        then(movieService).should().createMovie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);
    }

    @Test
    public void testCreateMovieShouldUnSuccessfullyCreatedMovie(){
        //Given
        String expected = "You are not admin.";
        given(accountLoginService.accountLoggedIn()).willReturn(false);

        //When
        String actual = underTest.createMovie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
    }

    @Test
    public void testListMoviesShouldListMovies(){
        //Given
        Movie movie = new Movie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);
        List<Movie> listMovie = List.of(movie);
        String expected = (TEST_TITLE + " (" + TEST_GENRE + ", "
                + TEST_LENGTH + " minutes)\n");
        given(movieService.getAllMovie()).willReturn(listMovie);

        //When
        String actual = underTest.listMovies();

        //Then
        assertEquals(expected, actual);
        then(movieService).should().getAllMovie();
    }

    @Test
    public void testDeleteMovieShouldAdminDeleteMovie(){
        //Given
        Movie movie = new Movie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);
        given(movieService.getMovie(TEST_TITLE)).willReturn(movie);
        given(accountLoginService.accountLoggedIn()).willReturn(true);
        String expected = TEST_TITLE + " movie deleted";

        //When
        String actual = underTest.deleteMovie(TEST_TITLE);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
        then(movieService).should().getMovie(TEST_TITLE);
    }

    @Test
    public void testDeleteMovieShouldNotAdminDeleteMovie(){
        //Given
        Movie movie = new Movie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);
        given(accountLoginService.accountLoggedIn()).willReturn(false);
        String expected = "You are not admin";

        //When
        String actual = underTest.deleteMovie(TEST_TITLE);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
    }

}