package com.epam.training.ticketservice.service.impl;

import static org.mockito.BDDMockito.given;

import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

class ScreeningServiceImplTest {

    private static final String TEST_TITLE = "test title";
    private static final String TEST_GENRE = "test genre";
    private static final String TEST_ROOM_NAME = "test room name";
    private static final String TEST_SCREENING_TIME = "test screening time";

    @InjectMocks
    ScreeningServiceImpl underTest;

    @Mock
    ScreeningRepository screeningRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllScreeningShouldFindAllScreening(){
        //Given
        Screening screening = new Screening(TEST_TITLE, TEST_GENRE, TEST_ROOM_NAME, TEST_SCREENING_TIME);
        List<Screening> expected = List.of(screening);
        given(screeningRepository.findAll()).willReturn(expected);

        //When
        List<Screening> actual = underTest.getAllScreening();

        //Then
        assertEquals(expected, actual);
        then(screeningRepository).should().findAll();
    }

    @Test
    public void testGetScreeningShouldFindScreeningByTitleAndRoomNameAndScreenTime(){
        //Given
        Screening expected = new Screening(TEST_TITLE, TEST_GENRE, TEST_ROOM_NAME, TEST_SCREENING_TIME);
        given(screeningRepository.findByTitleAndRoomNameAndScreenTime(TEST_TITLE, TEST_ROOM_NAME, TEST_SCREENING_TIME)).willReturn(expected);

        //When
        Screening actual = underTest.getScreening(TEST_TITLE, TEST_ROOM_NAME, TEST_SCREENING_TIME);

        //Then
        assertEquals(expected, actual);
        then(screeningRepository).should().findByTitleAndRoomNameAndScreenTime(TEST_TITLE, TEST_ROOM_NAME, TEST_SCREENING_TIME);
    }

    @Test
    public void testDeleteScreeningShouldDelete(){
        //Given
        Screening expected = new Screening(TEST_TITLE, TEST_GENRE, TEST_ROOM_NAME, TEST_SCREENING_TIME);

        //When
        underTest.deleteScreening(expected);

        //Then
        then(screeningRepository).should().delete(expected);
    }

    @Test
    public void testCreateScreeningShouldSaveScreening(){
        //Given
        Screening expected = new Screening(TEST_TITLE, TEST_GENRE, TEST_ROOM_NAME, TEST_SCREENING_TIME);
        given(screeningRepository.save(expected)).willReturn(expected);

        //When
        Screening actual = underTest.createScreening(TEST_TITLE, TEST_GENRE, TEST_ROOM_NAME, TEST_SCREENING_TIME);

        //Then
        assertEquals(expected, actual);
        then(screeningRepository).should().save(expected);

    }

}