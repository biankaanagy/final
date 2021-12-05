package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.service.ScreeningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Date;
import java.util.List;

@ShellComponent
public class ScreeningCommandHandle {

    private List<Screening> screenings;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreeningCommandHandle.class);

    private final ScreeningService screeningService;

    public ScreeningCommandHandle(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @ShellMethod(value = "Screening list", key = "list screenings")
    public void listScreens() {
        screenings = screeningService.getScreenings();
        for (int i = 0; i < screenings.size(); i++) {
            LOGGER.info(screenings.get(i).toString());
        }
    }

    @ShellMethod(value = "Screening creator", key = "create screening")
    public void createMovie(String title, String room, Date date) {
        screeningService.createScreening(title, room, date);
        LOGGER.info("Created Screening "+title+" "+room+" "+date);
    }
}
