package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.service.Observer;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private List<Screening> SCREENS;

    public ScreeningServiceImpl() {
        SCREENS = new ArrayList<>();
    }

    @Override
    public List<Screening> getScreenings() {
        return new ArrayList<>(SCREENS);
    }

    @Override
    public void createScreening(String title, String room, Date date) {
        SCREENS.add(Screening.builder().withTitle(title).withRoomName(room).withScreenTime(date).build());
    }

    @Override
    public void subscribe(Observer observer) {
    }
}
