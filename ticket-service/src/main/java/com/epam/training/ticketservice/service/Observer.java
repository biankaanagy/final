package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.model.Screening;

public interface Observer {

    void notify(Screening screening);
}
