package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Room;
import java.util.List;

public interface RoomService extends Observable{

    List<Room> getRooms();

    void createRoom(String name, int rowOfChairs, int columnOfChairs);
}
