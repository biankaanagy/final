package com.epam.training.ticketservice.presentation.cli.handler;

import static org.mockito.BDDMockito.given;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

class RoomCommandHandlerTest {

    private static final String TEST_ROOM_NAME = "name";
    private static final int TEST_ROOM_ROW = 2;
    private static final int TEST_ROOM_COL = 2;
    @InjectMocks
    private RoomCommandHandler underTest;

    @Mock
    private RoomService roomService;

    @Mock
    private AccountLoginService accountLoginService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRoomShouldAdminCreatedRoom(){
        //Given
        String expected = "Created Room "+TEST_ROOM_NAME+" "+TEST_ROOM_ROW+" "+TEST_ROOM_COL;
        Room room = new Room(TEST_ROOM_NAME, TEST_ROOM_ROW, TEST_ROOM_COL);
        given(accountLoginService.accountLoggedIn()).willReturn(true);
        given(roomService.createRoom(TEST_ROOM_NAME, TEST_ROOM_ROW, TEST_ROOM_COL)).willReturn(room);

        //When
        String actual = underTest.createRoom(TEST_ROOM_NAME, TEST_ROOM_ROW, TEST_ROOM_COL);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
        then(roomService).should().createRoom(TEST_ROOM_NAME, TEST_ROOM_ROW, TEST_ROOM_COL);
    }

    @Test
    public void testCreateRoomShouldNotAdminCreatedRoom(){
        //Given
        String expected = "You are not admin.";
        given(accountLoginService.accountLoggedIn()).willReturn(false);

        //When
        String actual = underTest.createRoom(TEST_ROOM_NAME, TEST_ROOM_ROW, TEST_ROOM_COL);

        //Then
        assertEquals(expected, actual);
        then(accountLoginService).should().accountLoggedIn();
    }

    @Test
    public void testListRoomsShouldListRooms(){
        //Given
        Room room = new Room(TEST_ROOM_NAME, TEST_ROOM_ROW, TEST_ROOM_COL);
        List<Room> roomList = List.of(room);
        given(roomService.getAllRoom()).willReturn(roomList);
        String expected = ("Room "+TEST_ROOM_NAME+" with "+(TEST_ROOM_ROW*TEST_ROOM_COL)+" seats, "
                +TEST_ROOM_ROW+" rows and "+TEST_ROOM_COL+" columns\n");

        //When
        String actual = underTest.listRooms();

        //Then
        assertEquals(expected, actual);
        then(roomService).should().getAllRoom();
    }

    @Test
    public void testListRoomsShouldListRoomsWhenEmpty(){
        //Given
        List<Room> roomList = List.of();
        given(roomService.getAllRoom()).willReturn(roomList);
        String expected = "There are no rooms at the moment";

        //When
        String actual = underTest.listRooms();

        //Then
        assertEquals(expected, actual);
        then(roomService).should().getAllRoom();
    }




}