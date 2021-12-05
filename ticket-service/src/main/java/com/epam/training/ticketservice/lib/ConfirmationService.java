package com.epam.training.ticketservice.lib;

import com.epam.training.ticketservice.model.Movie;

import java.util.List;

public interface ConfirmationService {

    void sendConfirmationMessageAbout(List<Movie> movies);
}
