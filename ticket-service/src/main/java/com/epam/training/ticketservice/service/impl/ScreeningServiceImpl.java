package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.model.Screening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.Observer;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository, MovieRepository movieRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Screening> getAllScreening() {
        return screeningRepository.findAll();
    }

    @Override
    public void deleteScreening(Screening screening) {
        screeningRepository.delete(screening);
    }

    @Override
    public Screening createScreening(String title, String a, String roomName, String screenTime) {
        Screening screening = Screening.builder().withTitle(title).withGenremovie(a).withRoomName(roomName).withScreenTime(screenTime).build();
        screeningRepository.save(screening);
        return screening;
    }

    @Override
    public Screening getScreening(String title, String roomName, String screenTime) {
        return screeningRepository.findByTitleAndRoomNameAndScreenTime(title, roomName, screenTime);
    }

    @Override
    public void subscribe(Observer observer) {}
}
