package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.service.Observer;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private List<Room> ROOMS;

    public RoomServiceImpl() {
        ROOMS = new ArrayList<>();
    }

    @Override
    public List<Room> getRooms() {
        return new ArrayList<>(ROOMS);
    }

    @Override
    public void createRoom(String name, int rowOfChairs, int columnOfChairs) {
        ROOMS.add(Room.builder().withName(name).withRow(rowOfChairs).withCol(columnOfChairs).build());
    }

    @Override
    public void subscribe(Observer observer) {}
}
