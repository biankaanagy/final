package com.epam.training.ticketservice.lib.impl;

import com.epam.training.ticketservice.lib.ConfirmationService;
import com.epam.training.ticketservice.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailConfirmationService implements ConfirmationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConfirmationService.class);

    @Override
    public void sendConfirmationMessageAbout(List<Movie> movies) {
        LOGGER.info("Sending an e-mail confirmation about {} movies", movies);
    }
}
