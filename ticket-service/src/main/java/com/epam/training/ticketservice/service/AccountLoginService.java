package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.service.exception.NoSuchAccountException;

public interface AccountLoginService extends Observable {

    boolean login(String name, String pwd) throws NoSuchAccountException;
    void signOut();
    String getUsername();
    boolean accountLoggedIn();
}
