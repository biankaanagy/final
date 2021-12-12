package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.Movie;
import com.epam.training.ticketservice.model.Room;
import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class RoomCommandHandler {

    private final RoomService roomService;
    private final AccountLoginService accountLoginService;

    private List<Room> rooms;

    public RoomCommandHandler(RoomService roomService, AccountLoginService accountLoginService) {
        this.roomService = roomService;
        this.accountLoginService = accountLoginService;
    }

    @ShellMethod(value = "Room creator", key = "create room")
    public String createRoom(String name, int rowOfChairs, int columnOfChairs) {
        if(accountLoginService.accountLoggedIn()){
            roomService.createRoom(name, rowOfChairs, columnOfChairs);
            return "Created Room "+name+" "+rowOfChairs+" "+columnOfChairs;
        }
        return "You are not admin.";
    }

    @ShellMethod(value = "Room list", key = "list rooms")
    public String listRooms() {
        rooms = roomService.getAllRoom();
        if(!rooms.isEmpty()){
            StringBuilder list = new StringBuilder();
            for (Room r: rooms) {
                list.append("Room "+r.getName()+" with "+r.getChairNumber()+" seats, "+r.getRowOfChairs()+" rows and "+r.getColumnOfChairs()+" columns\n");
            }
            return list+"";
        }
        return "There are no rooms at the moment";
    }

    @ShellMethod(value = "Room updator", key = "update room")
    public String updateRoom(String name, int rowOfChairs, int columnOfChairs) {
        if(accountLoginService.accountLoggedIn()){
            roomService.updateRoom(name, rowOfChairs, columnOfChairs);
            return name + " room updateed.";
        }
        return "You are not admin.";
    }

    @ShellMethod(value = "Room deleter", key = "delete room")
    public String deleteRoom(String name) {
        if(accountLoginService.accountLoggedIn()){
            try{
                Room deleteRoom = roomService.getRoom(name);
                roomService.deleteRoom(deleteRoom);
                return deleteRoom.getName()+" room deleted";
            }catch (NullPointerException e){
                return "No such room: "+ name;
            }
        }
        return "You are not admin.";
    }
}