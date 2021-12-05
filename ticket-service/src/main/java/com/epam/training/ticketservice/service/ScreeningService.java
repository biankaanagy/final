package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Screening;

import java.util.Date;
import java.util.List;

public interface ScreeningService extends Observable{
    List<Screening> getScreenings();
    void createScreening(String title, String roomName, Date screenTime);
}