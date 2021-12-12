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

    private final List<Observer> observers;
    private String userName;
    private boolean loggedIn = false;

    public AccountLoginServiceImpl() {
        this.observers = new ArrayList<>();
    }

    @Override
    public boolean login(String name, String password) throws NoSuchAccountException {
        if(name.equals("admin") && password.equals("admin")){
            this.userName = name;
            this.loggedIn = true;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void signOut() {
        this.loggedIn = false;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean accountLoggedIn() {
        return loggedIn;
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }
}
