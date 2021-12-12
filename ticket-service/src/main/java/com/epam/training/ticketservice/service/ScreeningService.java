package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Screening;

import java.util.List;

public interface ScreeningService extends Observable{

    List<Screening> getAllScreening();

    void deleteScreening(Screening screening);

    Screening createScreening(String title, String a, String roomName, String screenTime);

    Screening getScreening(String title, String roomName, String screenTime);
}