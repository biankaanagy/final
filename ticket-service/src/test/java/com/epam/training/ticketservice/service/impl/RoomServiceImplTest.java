package com.epam.training.ticketservice.service.impl;

import static org.mockito.BDDMockito.given;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

class RoomServiceImplTest {

    private static final String TEST_NAME = "test room";
    private static final int TEST_ROWOFCHAIRS = 5;
    private static final int TEST_COLLOFCHAIRS = 5;
    private static final int TEST_UPDATE_ROWOFCHAIRS = 3;
    private static final int TEST_UPDATE_COLLOFCHAIRS = 2;

    @InjectMocks
    private RoomServiceImpl underTest;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRoomShouldFindRoom(){
        //Given
        Room expected = new Room(TEST_NAME, TEST_ROWOFCHAIRS, TEST_COLLOFCHAIRS);
        given(roomRepository.findByName(TEST_NAME)).willReturn(expected);

        //When
        Room actual = underTest.getRoom(TEST_NAME);

        //Then
        assertEquals(expected, actual);
        then(roomRepository).should().findByName(TEST_NAME);
    }

    @Test
    public void testGetAllRoomShouldFindAllRoom(){
        //Given
        Room room = new Room(TEST_NAME, TEST_ROWOFCHAIRS, TEST_COLLOFCHAIRS);
        List<Room> expected = List.of(room);
        given(roomRepository.findAll()).willReturn(expected);

        //When
        List<Room> actual = underTest.getAllRoom();

        //Then
        assertEquals(expected, actual);
        then(roomRepository).should().findAll();
    }

    @Test
    public void testCreateRoomShouldSaveMovie() {
        //Given
        Room expected = new Room(TEST_NAME, TEST_ROWOFCHAIRS, TEST_COLLOFCHAIRS);
        given(roomRepository.save(expected)).willReturn(expected);

        //When
        Room actual = underTest.createRoom(TEST_NAME, TEST_ROWOFCHAIRS, TEST_COLLOFCHAIRS);

        //Then
        assertEquals(expected, actual);
        then(roomRepository).should().save(expected);
    }

    @Test
    public void testUpdateRoomShouldUpdateRoomWhenPresent(){
        //Given
        Room room = new Room(TEST_NAME, TEST_ROWOFCHAIRS, TEST_COLLOFCHAIRS);
        given(roomRepository.findByName(TEST_NAME)).willReturn(room);

        Room expected = new Room(TEST_NAME, TEST_UPDATE_ROWOFCHAIRS, TEST_UPDATE_COLLOFCHAIRS);
        given(roomRepository.save(expected)).willReturn(expected);

        //When
        Room actual = underTest.updateRoom(TEST_NAME, TEST_UPDATE_ROWOFCHAIRS, TEST_UPDATE_COLLOFCHAIRS);

        //Then
        assertEquals(expected, actual);
        then(roomRepository).should().findByName(TEST_NAME);
        then(roomRepository).should().save(expected);
    }

    @Test
    public void testUpdateRoomShouldUpdateRoomWhenRoomNotPresent(){
        //Given

        //When

        //Then

    }

    @Test
    public void testDeleteRoomShouldDelete(){
        //Given
        Room expected = new Room(TEST_NAME, TEST_ROWOFCHAIRS, TEST_COLLOFCHAIRS);

        //When
        underTest.deleteRoom(expected);

        //Then
        then(roomRepository).should().delete(expected);

    }

}