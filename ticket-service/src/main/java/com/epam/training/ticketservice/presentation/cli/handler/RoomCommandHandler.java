package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class RoomCommandHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomCommandHandler.class);

    private final RoomService roomService;

    private List<Room> rooms;

    public RoomCommandHandler(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethod(value = "Room creator", key = "create room")
    public void createMovie(String title, int rowOfChairs, int columnOfChairs) {
        roomService.createRoom(title, rowOfChairs, columnOfChairs);
        LOGGER.info("Created Room "+title+" "+rowOfChairs+" "+columnOfChairs);
    }

    @ShellMethod(value = "Room list", key = "list rooms")
    public void listMovies() {
        rooms = roomService.getRooms();
        for (int i = 0; i < rooms.size(); i++) {
            LOGGER.info(rooms.get(i).toString());
        }
    }
}