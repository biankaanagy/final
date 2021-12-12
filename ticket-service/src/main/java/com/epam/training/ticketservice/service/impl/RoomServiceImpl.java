package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.service.Observer;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room getRoom(String room) {
        return roomRepository.findByName(room);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room createRoom(String name, int rowOfChairs, int columnOfChairs) {
        //Room room = Room.builder().withName(name).withRow(rowOfChairs).withCol(columnOfChairs).build();
        Room room = new Room(name, rowOfChairs, columnOfChairs);
        roomRepository.save(room);
        return room;
    }

    @Override
    public Room updateRoom(String name, int rowOfChairs, int columnOfChairs) {
        Room roomUpdate = roomRepository.findByName(name);
        roomUpdate.setRowOfChairs(rowOfChairs);
        roomUpdate.setChairNumber(rowOfChairs*columnOfChairs);
        roomUpdate.setColumnOfChairs(columnOfChairs);
        roomRepository.save(roomUpdate);
        return roomUpdate;
    }

    @Override
    public void deleteRoom(Room name) {
        roomRepository.delete(name);
    }

    @Override
    public void subscribe(Observer observer) {}
}
