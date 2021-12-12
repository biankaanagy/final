package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Room;

import java.util.List;

public interface RoomService extends Observable{

    List<Room> getAllRoom();

    Room createRoom(String name, int rowOfChairs, int columnOfChairs);

    Room updateRoom(String name, int rowOfChairs, int columnOfChairs);

    void deleteRoom(Room name);

    Room getRoom(String room);

}
