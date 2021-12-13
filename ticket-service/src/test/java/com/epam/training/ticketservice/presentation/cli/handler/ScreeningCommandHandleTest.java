package com.epam.training.ticketservice.presentation.cli.handler;

import static org.mockito.BDDMockito.given;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

class ScreeningCommandHandleTest {

    private static final String TEST_TITLE = "title";
    private static final String TEST_ROOM = "room";
    private static final String TEST_SCREENING_TIME = "time";
    private static final String TEST_GENRE = "genre";
    private static final String TEST_LENGTH = "1";
    private static final int TEST_INT_LENGTH = 1;

    @InjectMocks
    private ScreeningCommandHandle underTest;

    @Mock
    private ScreeningService screeningService;

    @Mock
    private AccountLoginService accountLoginService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteScreeningShouldAdminDeleteScreening(){
        //Given
        String expected = "Screening deleted: " + TEST_TITLE + " " + TEST_ROOM + " " + TEST_SCREENING_TIME;
        Screening screening = new Screening(TEST_TITLE, TEST_GENRE, TEST_ROOM, TEST_SCREENING_TIME);
        given(accountLoginService.accountLoggedIn()).willReturn(true);
        given(screeningService.getScreening(TEST_TITLE, TEST_ROOM, TEST_SCREENING_TIME)).willReturn(screening);

        //When
        String actual = underTest.deleteScreening(TEST_TITLE, TEST_ROOM, TEST_SCREENING_TIME);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
        then(screeningService).should().getScreening(TEST_TITLE, TEST_ROOM, TEST_SCREENING_TIME);
    }

    @Test
    public void testDeleteScreeningShouldNotAdminDeleteScreening(){
        //Given
        String expected = "You are not admin";
        given(accountLoginService.accountLoggedIn()).willReturn(false);

        //When
        String actual = underTest.deleteScreening(TEST_TITLE, TEST_ROOM, TEST_SCREENING_TIME);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
    }
    /*
    @Test
    public void testListScreensShouldListScreenings(){
        //Given
        Screening screening = new Screening(TEST_TITLE, TEST_GENRE, TEST_ROOM, TEST_SCREENING_TIME);
        Movie movie = new Movie(TEST_TITLE, TEST_GENRE, TEST_INT_LENGTH);
        List<Screening> screeningsList = Arrays.asList(screening);
        String expected = (TEST_TITLE + " (" + TEST_GENRE + ", " + TEST_LENGTH + " minutes), screened in room "
                + TEST_ROOM + ", at " + TEST_SCREENING_TIME + "\n");
        given(screeningService.getAllScreening()).willReturn(screeningsList);

        //When
        String actual = underTest.listScreens();

        //Then
        assertEquals(expected, actual);
        then(screeningService).should().getAllScreening();
    }*/

    @Test
    public void testListScreensShouldListScreeningsWhenEmpty(){
        //Given
        String expected = "There are no screenings";

        //When
        String actual = underTest.listScreens();

        //Then
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateScreeningShouldNotAdminCreatedScreenings() throws ParseException {
        //Given
        String expected = "You are not admin";
        given(accountLoginService.accountLoggedIn()).willReturn(false);

        //When
        String actual = underTest.createScreening(TEST_TITLE, TEST_ROOM, TEST_SCREENING_TIME);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
    }

}