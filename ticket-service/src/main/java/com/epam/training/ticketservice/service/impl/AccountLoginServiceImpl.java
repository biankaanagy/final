package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.service.AccountLoginService;
import com.epam.training.ticketservice.service.Observer;
import com.epam.training.ticketservice.service.exception.NoSuchAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountLoginServiceImpl implements AccountLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountLoginServiceImpl.class);
    private final List<Observer> observers;
    private String userName;

    public AccountLoginServiceImpl() {
        this.observers = new ArrayList<>();
    }

    @Override
    public boolean login(String name, String pwd) throws NoSuchAccountException {
        if(name.equals("admin") && pwd.equals("admin")){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void signOut() {
        LOGGER.info("Signed out "+userName);
    }


    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }
}
